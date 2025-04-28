package com.scalerkart.ecommerce.services.order.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
public class DeliveryAddressDto {

    //@NotBlank(message = "Name is required")
    private String name;

    //@NotBlank(message = "Address is required")
    //@Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

    //@NotBlank(message = "City is required")
    private String city;

    //@NotBlank(message = "State is required")
    private String state;

    //@NotBlank(message = "Pincode is required")
   // @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Indian pincode")
    private String pincode;

   // @NotBlank(message = "Mobile number is required")
   // @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian mobile number")
    private String mobileNumber;

}