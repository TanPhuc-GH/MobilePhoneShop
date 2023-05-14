package com.hcmute.MobilePhoneShop.entities;

import com.hcmute.MobilePhoneShop.entities.embedded.ProductPrice;
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

    private String bandId;

    private List<String> color;

//    The price depends on storage and color of the product
    private List<ProductPrice> price;
}
