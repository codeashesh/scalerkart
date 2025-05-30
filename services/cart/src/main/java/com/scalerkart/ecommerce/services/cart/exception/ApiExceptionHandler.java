package com.scalerkart.ecommerce.services.cart.exception;

import com.scalerkart.ecommerce.services.cart.exception.payload.ExceptionMessage;
import com.scalerkart.ecommerce.services.cart.exception.wrapper.CartNotFoundException;
import com.scalerkart.ecommerce.services.cart.exception.wrapper.JwtAuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, JwtAuthenticationException.class})
    public <T extends BindException> ResponseEntity<ExceptionMessage> handleValidationException(final T e) {
        log.info("ApiExceptionHandler controller, handle validation exception");
        final var badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                ExceptionMessage.builder()
                        .message("*" + Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage() + "!**")
                        .httpStatus(badRequest)
                        .timestamp(ZonedDateTime
                                .now(ZoneId.systemDefault()))
                        .build(), badRequest);
    }

    @ExceptionHandler(value = {CartNotFoundException.class})
    public <T extends RuntimeException> ResponseEntity<ExceptionMessage> handleApiRequestException(final T e) {
        log.info("ApiExceptionHandler controller, handle API request");
        final var badRequest = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(
                ExceptionMessage.builder()
                        .message("#### " + e.getMessage() + "! ####")
                        .httpStatus(badRequest)
                        .timestamp(ZonedDateTime
                                .now(ZoneId.systemDefault()))
                        .build(), badRequest);
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public <T extends RuntimeException> ResponseEntity<ExceptionMessage> handleApiSaveDatabaseException(final T e) {
        log.info("ApiExceptionHandler controller, handle API save database");
        return new ResponseEntity<>(ExceptionMessage.builder()
                .message("#### " + e.getMessage() + "! ####")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}