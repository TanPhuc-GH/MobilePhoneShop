package com.hcmute.MobilePhoneShop.services.product;

import com.hcmute.MobilePhoneShop.dtos.ProductDTO;
import com.hcmute.MobilePhoneShop.dtos.ProductDetailDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    ProductDetailDTO getProductDetail(String id);
    Product create(ProductDTO productDTO);
    BaseResponse update(String id, ProductDTO productDTO);
    BaseResponse delete(String id);

}
