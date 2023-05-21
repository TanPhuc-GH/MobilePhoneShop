package com.hcmute.MobilePhoneShop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_detail")
public class OrderDetail {
    @Id
    private String id;

    private String productName;

    private Integer quantity;

    private Double price;

    private String color;

    private String storage;
}
