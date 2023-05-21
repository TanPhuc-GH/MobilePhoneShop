package com.hcmute.MobilePhoneShop.services.product;

import com.hcmute.MobilePhoneShop.dtos.ProductDTO;
import com.hcmute.MobilePhoneShop.dtos.ProductDetailDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getAllProduct(int page, int size);
    ProductDetailDTO getProductDetail(String id);
    Product create(ProductDTO productDTO);
    BaseResponse update(String id, ProductDTO productDTO);
    BaseResponse delete(String id);
    Page<Product> search(String keyword, int page, int size);
    Page<Product> filter(String filter, int page, int size);
}
