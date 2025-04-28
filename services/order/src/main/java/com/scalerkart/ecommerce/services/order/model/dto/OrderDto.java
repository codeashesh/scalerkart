package com.scalerkart.ecommerce.services.order.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    private String customerName;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian mobile number")
    private String mobileNumber;

    @Valid
    @NotNull(message = "Delivery address is required")
    private DeliveryAddressDto deliveryAddress;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    private String transactionId;

    @NotNull(message = "Order status is required")
    private String orderStatus;

    @NotNull(message = "Items list cannot be empty")
    @Size(min = 1, message = "At least one item is required")
    private List<OrderItemDto> items;

    @NotNull(message = "Total amount is required")
    @Positive(message = "Total amount must be positive")
    private BigDecimal totalAmount;

    private BigDecimal discount;

    private String estimatedDelivery;

    private String couponCode;

    private Long userId;

}