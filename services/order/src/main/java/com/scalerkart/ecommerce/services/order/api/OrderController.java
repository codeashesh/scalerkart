package com.scalerkart.ecommerce.services.order.api;

import com.scalerkart.ecommerce.services.order.model.dto.OrderDto;
import com.scalerkart.ecommerce.services.order.security.jwt.JwtProvider;
import com.scalerkart.ecommerce.services.order.service.OrderService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
@Tag(name = "OrderController", description = "Operations related to orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtProvider jwtProvider;

    @ApiOperation(value = "Get all orders", notes = "Retrieve a list of all orders.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Orders retrieved successfully", response = List.class),
            @ApiResponse(code = 204, message = "No content", response = ResponseEntity.class)
    })
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> findAll(@RequestHeader(name = "Authorization") String authorizationToken) {
        log.info("*** OrderDto List, controller; fetch all orders *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        return ResponseEntity.ok(orderService.findUserOrders(userId));
    }

    @ApiOperation(value = "Get order by ID", notes = "Retrieve order information based on the provided ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order retrieved successfully", response = OrderDto.class),
            @ApiResponse(code = 404, message = "Order not found", response = ResponseEntity.class)
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findById(@RequestHeader(name = "Authorization") String authorizationToken,
                                                   @PathVariable("orderId")
                                                   @NotBlank(message = "Input must not be blank")
                                                   @Valid final String orderId) {
        log.info("*** OrderDto, resource; fetch order by id *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        return ResponseEntity.ok(orderService.findUserSpecificOrder(userId, Long.valueOf(orderId)));
    }

    @ApiOperation(value = "Save order", notes = "Save a new order.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order saved successfully", response = OrderDto.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseEntity.class)
    })
    @PostMapping("/cart")
    public ResponseEntity<OrderDto> createCartOrder(@RequestHeader(name = "Authorization") String authorizationToken,
                                                    @RequestBody
                                                    @NotNull(message = "Input must not be NULL")
                                                    final OrderDto orderDto) {
        log.info("*** OrderDto, resource; save order *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        orderDto.setUserId(userId);
        return ResponseEntity.ok(orderService.createCartOrder(orderDto));
    }

    @ApiOperation(value = "Save order", notes = "Save a new order.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order saved successfully", response = OrderDto.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseEntity.class)
    })
    @PostMapping("/product")
    public ResponseEntity<OrderDto> createProductOrder(@RequestHeader(name = "Authorization") String authorizationToken,
                                                       @RequestBody
                                                       @NotNull(message = "Input must not be NULL")
                                                       @Valid final OrderDto orderDto) {
        log.info("*** OrderDto, resource; save order *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        orderDto.setUserId(userId);
        return ResponseEntity.ok(orderService.createProductOrder(orderDto));
    }

    @ApiOperation(value = "Update order", notes = "Update an existing order.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order updated successfully", response = OrderDto.class),
            @ApiResponse(code = 404, message = "Order not found", response = ResponseEntity.class)
    })
    @PutMapping
    public ResponseEntity<OrderDto> update(@RequestHeader(name = "Authorization") String authorizationToken,
                                           @RequestBody
                                           @NotNull(message = "Input must not be NULL")
                                           @Valid final OrderDto orderDto) {
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        orderDto.setUserId(userId);
        log.info("*** OrderDto, resource; update order *");
        return ResponseEntity.ok(orderService.modifyOrder(orderDto));
    }

    @ApiOperation(value = "Update order by ID", notes = "Update an existing order based on the provided ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order updated successfully", response = OrderDto.class),
            @ApiResponse(code = 404, message = "Order not found", response = ResponseEntity.class)
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> update(@RequestHeader(name = "Authorization") String authorizationToken,
                                           @PathVariable("orderId")
                                           @NotBlank(message = "Input must not be blank")
                                           @Valid final String orderId,
                                           @RequestBody
                                           @NotNull(message = "Input must not be NULL")
                                           @Valid final OrderDto orderDto) {
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        orderDto.setUserId(userId);
        orderDto.setId(Long.valueOf(orderId));
        log.info("*** OrderDto, resource; update order with orderId *");
        return ResponseEntity.ok(orderService.modifyOrder(orderDto));
    }

    @ApiOperation(value = "Delete order by ID", notes = "Delete an order based on the provided ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Order deleted successfully", response = Boolean.class),
            @ApiResponse(code = 404, message = "Order not found", response = ResponseEntity.class)
    })
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteById(@RequestHeader(name = "Authorization") String authorizationToken,
                                              @PathVariable("orderId") final String orderId) {
        log.info("*** Boolean, resource; delete order by id *");
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        orderService.cancelUserSpecificOrder(userId, Long.valueOf(orderId));
        return ResponseEntity.ok(true);
    }

    @GetMapping("/exist/{orderId}")
    public Boolean existsByOrderId(@RequestHeader(name = "Authorization") String authorizationToken,
                                   @PathVariable("orderId")
                                   @NotBlank(message = "Input must not be blank")
                                   @Valid final String orderId) {
        Long userId = jwtProvider.getUserIdFromToken(authorizationToken.substring(7));
        return orderService.existsByOrderId(userId, Long.valueOf(orderId));
    }

}
