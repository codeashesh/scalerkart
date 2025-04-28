package com.scalerkart.ecommerce.services.order.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private static final List<AntPathRequestMatcher> AUTH_WHITELIST_MATCHERS = List.of(
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/health")
    );

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // Skip JWT filtering for whitelisted endpoints
            if (AUTH_WHITELIST_MATCHERS.stream().anyMatch(matcher -> matcher.matches(request))) {
                logger.info("Filter skipped");
                filterChain.doFilter(request, response);
                return;
            }

            String token = getToken(request);
            if (token != null && jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                request.setAttribute("JWT_TOKEN", token);
                filterChain.doFilter(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            }
        } catch (ExpiredJwtException e) {
            logger.error("Token has expired for request: {}", request.getRequestURI());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token has expired");
        } catch (MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        } catch (Exception e) {
            logger.error("Can't set user authentication -> Message: ", e);
        }
    }

    public static String getTokenFromRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return (String) request.getAttribute("JWT_TOKEN");
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }

}
