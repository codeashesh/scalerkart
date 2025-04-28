package com.scalerkart.ecommerce.services.order.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public final class DeliveryAddress {

    @Column(name = "recipient_name")
    private String name;
    private String address;
    private String city;
    private String state;
    private String pincode;
    @Column(name = "delivery_mobile_number")
    private String mobileNumber;

}

