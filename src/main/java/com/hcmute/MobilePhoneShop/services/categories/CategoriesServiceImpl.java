package com.hcmute.MobilePhoneShop.services.categories;

import com.hcmute.MobilePhoneShop.dtos.CategoriesDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Categories;
import com.hcmute.MobilePhoneShop.exceptions.InvalidException;
import com.hcmute.MobilePhoneShop.exceptions.NotFoundException;
import com.hcmute.MobilePhoneShop.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService{

    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories;
    }

    @Override
    public Categories getCategory(String id) {
        return categoriesRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Categories with id %s not found",id)));
    }

    @Override
    public Categories create(CategoriesDTO categoriesDTO) {
        if (ObjectUtils.isEmpty(categoriesDTO.getCategoryName())){
            throw new InvalidException("Categories name no empty");
        }
        Categories categories = new Categories();
        categories.setCategoryName(categoriesDTO.getCategoryName().trim());
        categoriesRepository.save(categories);
        return categories;
    }

    @Override
    public BaseResponse update(String id, CategoriesDTO categoriesDTO) {
        Categories categories = categoriesRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Categories with id %s not found",id)));
        categories.setCategoryName(categoriesDTO.getCategoryName());
        categoriesRepository.save(categories);
        return new BaseResponse(true, "Update successful");
    }
}
