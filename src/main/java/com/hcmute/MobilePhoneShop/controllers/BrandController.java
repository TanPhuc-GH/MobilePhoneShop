package com.hcmute.MobilePhoneShop.controllers;

import com.hcmute.MobilePhoneShop.dtos.BrandDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Brand;
import com.hcmute.MobilePhoneShop.services.brand.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrand(){
        return new ResponseEntity<>(brandService.getAllBrand(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable String id){
        return new ResponseEntity<>(brandService.getBrand(id),HttpStatus.OK);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Brand> createBrand(@RequestBody BrandDTO brandDTO){
        return new ResponseEntity<>(brandService.create(brandDTO), HttpStatus.OK);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable String id, @RequestBody BrandDTO brandDTO){
        try {
            return ResponseEntity.ok(brandService.update(id, brandDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false, e.getMessage()));
        }
    }
}
