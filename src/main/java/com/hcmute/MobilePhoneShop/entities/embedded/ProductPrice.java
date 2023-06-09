package com.hcmute.MobilePhoneShop.entities.embedded;

import com.hcmute.MobilePhoneShop.utils.EnumColors;
import com.hcmute.MobilePhoneShop.utils.EnumStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {
    private String storage;

    private String colors;

    private double price;
}
