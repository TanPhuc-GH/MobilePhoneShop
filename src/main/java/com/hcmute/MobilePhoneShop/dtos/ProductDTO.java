package com.hcmute.MobilePhoneShop.dtos;

import com.hcmute.MobilePhoneShop.entities.embedded.ProductPrice;
import com.hcmute.MobilePhoneShop.entities.embedded.QuantityOfColor;
import com.hcmute.MobilePhoneShop.utils.EnumStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productName;

    private Integer quantity;

    private String description;

    private List<String> storages;

    private List<QuantityOfColor> quantityOfColors;

    private String categoriesId;

    private String bandId;

    private List<String> color;

    private List<ProductPrice> price;

    private Boolean disable = false;
}
