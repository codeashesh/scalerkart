package com.scalerkart.ecommerce.services.order.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scalerkart.ecommerce.services.order.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public final class Order extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;

    @Embedded
    private DeliveryAddress deliveryAddress;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "discount", precision = 10, scale = 2)
    private BigDecimal discount;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "estimated_delivery")
    private String estimatedDelivery;

}

