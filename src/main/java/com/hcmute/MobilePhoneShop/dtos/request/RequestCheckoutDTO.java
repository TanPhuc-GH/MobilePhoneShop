package com.hcmute.MobilePhoneShop.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCheckoutDTO {
    private String userId;

    private String productId;

    private Integer quantity;

    private Double price;

    private String color;

    private String storage;
}
