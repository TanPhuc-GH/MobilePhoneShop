package com.hcmute.MobilePhoneShop.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuantityOfColor {
    private String color;

    private String storage;

    private Integer quantity;

    private Boolean sold_out = false;
}
