package com.hcmute.MobilePhoneShop.services.categories;

import com.hcmute.MobilePhoneShop.dtos.CategoriesDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> getAllCategories();
    Categories getCategory(String id);
    Categories create(CategoriesDTO categoriesDTO);
    BaseResponse update(String id, CategoriesDTO categoriesDTO);
}
