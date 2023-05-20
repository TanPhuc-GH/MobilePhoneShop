package com.hcmute.MobilePhoneShop.dtos;

import com.hcmute.MobilePhoneShop.entities.Product;
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
public class ProductDetailDTO {
    private String productName;

    private Integer quantity;

    private String description;

    private String bandName;

    private List<String> color;

    private List<QuantityOfColor> quantityOfColors;

    private List<ProductPrice> price;

    private List<String> storage;

    public ProductDetailDTO(Product product, String brandName){
        this.setProductName(product.getProductName());
        this.setQuantity(product.getQuantity());
        this.setDescription(product.getDescription());
        this.setQuantityOfColors(product.getQuantityOfColors());
        this.setBandName(brandName);
        this.setColor(product.getColor());
        this.setPrice(product.getPrice());
        this.setStorage(product.getStorages());
    }
}
