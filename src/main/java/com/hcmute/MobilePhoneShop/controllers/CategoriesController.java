package com.hcmute.MobilePhoneShop.controllers;

import com.hcmute.MobilePhoneShop.dtos.CategoriesDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Categories;
import com.hcmute.MobilePhoneShop.services.categories.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories(){
        return new ResponseEntity<>(categoriesService.getAllCategories(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategories(@PathVariable String id){
        return new ResponseEntity<>(categoriesService.getCategory(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Categories> create(@RequestBody CategoriesDTO categoriesDTO){
        return new ResponseEntity<>(categoriesService.create(categoriesDTO), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> upadate(@PathVariable String id, @RequestBody CategoriesDTO categoriesDTO){
        try {
            return ResponseEntity.ok(categoriesService.update(id, categoriesDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false, e.getMessage()));
        }
    }
}
