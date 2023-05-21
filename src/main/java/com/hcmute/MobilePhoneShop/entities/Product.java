package com.hcmute.MobilePhoneShop.entities;

import com.hcmute.MobilePhoneShop.entities.embedded.ProductPrice;
import com.hcmute.MobilePhoneShop.entities.embedded.QuantityOfColor;
import com.hcmute.MobilePhoneShop.utils.EnumStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String id;

    private String productName;

    private Integer quantity;

    private String description;

    private Boolean sold_out = false;

    private List<String> storages;

    private List<QuantityOfColor> quantityOfColors;

    private String categoriesId;

    private String brandId;

    private List<String> color;

//    The price depends on storage and color of the product
    private List<ProductPrice> price;

    private Boolean disable = false;
}
