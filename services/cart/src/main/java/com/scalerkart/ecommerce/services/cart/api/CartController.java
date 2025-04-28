package com.scalerkart.ecommerce.services.cart.api;

import com.scalerkart.ecommerce.services.cart.model.dto.CartDto;
import com.scalerkart.ecommerce.services.cart.model.dto.CartItemDto;
import com.scalerkart.ecommerce.services.cart.security.jwt.JwtProvider;
import com.scalerkart.ecommerce.services.cart.service.CartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
@Tag(name = "CartController", description = "Operations related to carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtProvider jwtProvider;

    @ApiOperation(value = "Get all carts", notes = "Retrieve a list of all carts.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Carts retrieved successfully", response = List.class),
            @ApiResponse(code = 204, message = "No content", response = ResponseEntity.class)
    })
    @GetMapping("/admin")
    public ResponseEntity<List<CartDto>> findAll() {
        log.info("*** CartDto List, controller; fetch all categories *");
        return ResponseEntity.ok(cartService.findAll());
    }

    @ApiOperation(value = "Get cart", notes = "Retrieve a cart for user.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart retrieved successfully", response = Page.class),
            @ApiResponse(code = 204, message = "No content", response = ResponseEntity.class)
    })
    @GetMapping
    public ResponseEntity<CartDto> findAll(@RequestHeader(name = "Authorization") String authorizationToken) {
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        log.info("Cart retrieval for user: "+ userId);
        return ResponseEntity.ok(cartService.findByUser(userId));
    }

    @ApiOperation(value = "Get cart items", notes = "Retrieve cart items for user.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart Items retrieved successfully", response = CartDto.class),
            @ApiResponse(code = 404, message = "Cart not found", response = ResponseEntity.class)
    })
    @GetMapping("/item")
    public ResponseEntity<List<CartItemDto>> findCartItems(@RequestHeader(name = "Authorization") String authorizationToken) {
        log.info("*** CartDto, resource; fetch cart by id *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        log.info("Cart retrieval for user: "+ userId);
        return ResponseEntity.ok(this.cartService.findItems(userId));
    }


    @ApiOperation(value = "Save cart", notes = "Save a new cart.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart saved successfully", response = CartDto.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseEntity.class)
    })
    @PostMapping("/item")
    public ResponseEntity<CartDto> save(@RequestHeader(name = "Authorization") String authorizationToken,
                                              @RequestBody
                                              @NotNull(message = "Input must not be NULL!")
                                              @Valid final CartItemDto itemDto) {
        log.info("*** CartDto, resource; save cart *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        log.info("Cart retrieval for user: "+ userId);
        return ResponseEntity.ok(cartService.addItemToCart(userId, itemDto));
    }

    @ApiOperation(value = "Update cart", notes = "Update an existing cart.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart updated successfully", response = CartDto.class),
            @ApiResponse(code = 404, message = "Cart not found", response = ResponseEntity.class)
    })
    @PutMapping("/item")
    public ResponseEntity<CartDto> update(@RequestHeader(name = "Authorization") String authorizationToken,
                                                @RequestBody
                                                @NotNull(message = "Input must not be NULL!")
                                                @Valid final CartItemDto itemDto) {
        log.info("*** CartDto, resource; save cart *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        log.info("Cart retrieval for user: "+ userId);
        return ResponseEntity.ok(cartService.updateItemInCart(userId, itemDto));
    }

    @ApiOperation(value = "Delete cart by ID", notes = "Delete a cart based on the provided ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart deleted successfully", response = Boolean.class),
            @ApiResponse(code = 404, message = "Cart not found", response = ResponseEntity.class)
    })
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<CartDto> deleteById(@RequestHeader(name = "Authorization") String authorizationToken,
                                              @PathVariable("cartItemId")
                                              @NotBlank(message = "Input must not be blank")
                                              @Valid final String cartItemId) {
        log.info("*** Boolean, resource; delete cart by id *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        log.info("Cart retrieval for user: "+ userId);
        return ResponseEntity.ok(cartService.removeItemInCart(userId, Long.valueOf(cartItemId)));
    }

    @ApiOperation(value = "Delete cart by ID", notes = "Delete a cart based on the provided ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart deleted successfully", response = Boolean.class),
            @ApiResponse(code = 404, message = "Cart not found", response = ResponseEntity.class)
    })
    @DeleteMapping("/item")
    public ResponseEntity<CartDto> deleteById(@RequestHeader(name = "Authorization") String authorizationToken) {
        log.info("*** Boolean, resource; delete cart by id *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        log.info("Cart retrieval for user: "+ userId);
        return ResponseEntity.ok(cartService.removeAllItemInCart(userId));
    }

}
