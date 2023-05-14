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
@Document(collection = "brand")
public class Brand {
    @Id
    private String id;

    private String brandName;

    private String website;

    private String contact;

    private String country;
}
