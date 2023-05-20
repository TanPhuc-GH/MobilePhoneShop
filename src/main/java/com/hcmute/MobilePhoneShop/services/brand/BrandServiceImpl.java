package com.hcmute.MobilePhoneShop.services.brand;

import com.hcmute.MobilePhoneShop.dtos.BrandDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Brand;
import com.hcmute.MobilePhoneShop.exceptions.InvalidException;
import com.hcmute.MobilePhoneShop.exceptions.NotFoundException;
import com.hcmute.MobilePhoneShop.repositories.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    BrandRepository brandRepository;

    @Override
    public List<Brand> getAllBrand() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    @Override
    public Brand getBrand(String id) {
        return brandRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Brand with id %s not found", id)));
    }

    @Override
    public Brand create(BrandDTO brandDTO) {
        if (ObjectUtils.isEmpty(brandDTO.getBrandName())){
            throw new InvalidException("Tên thương hiệu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(brandDTO.getWebsite())){
            throw new InvalidException("Website của thương hiệu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(brandDTO.getContact())){
            throw new InvalidException("Thông tin liên hệ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(brandDTO.getCountry())){
            throw new InvalidException("Quốc gia không được bỏ trống");
        }

        Brand brand = new Brand();
        brand.setBrandName(brandDTO.getBrandName());
        brand.setWebsite(brandDTO.getWebsite());
        brand.setContact(brandDTO.getContact());
        brand.setCountry(brandDTO.getCountry());
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public BaseResponse update(String id, BrandDTO brandDTO) {
        Brand brand = getBrand(id);
        if (ObjectUtils.isEmpty(brandDTO.getBrandName())){
            throw new InvalidException("Tên thương hiệu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(brandDTO.getWebsite())){
            throw new InvalidException("Website của thương hiệu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(brandDTO.getContact())){
            throw new InvalidException("Thông tin liên hệ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(brandDTO.getCountry())){
            throw new InvalidException("Quốc gia không được bỏ trống");
        }
        if(!brand.getBrandName().equalsIgnoreCase(brandDTO.getBrandName()) && brandRepository.existsByBrandName(brandDTO.getBrandName())){
            throw new InvalidException("tên thương hiệu đã tồn tại");
        }
        brand.setBrandName(brandDTO.getBrandName());
        brand.setWebsite(brandDTO.getWebsite());
        brand.setContact(brandDTO.getContact());
        brand.setCountry(brandDTO.getCountry());
        brandRepository.save(brand);
        return new BaseResponse(true,"Update successful");
    }
}
