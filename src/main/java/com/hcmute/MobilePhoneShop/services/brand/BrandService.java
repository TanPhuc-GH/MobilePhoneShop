package com.hcmute.MobilePhoneShop.services.brand;

import com.hcmute.MobilePhoneShop.dtos.BrandDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrand();
    Brand getBrand(String id);
    Brand create(BrandDTO brandDTO);
    BaseResponse update(String id, BrandDTO brandDTO);
}
