package com.hcmute.MobilePhoneShop.controllers;

import com.hcmute.MobilePhoneShop.dtos.ProductDTO;
import com.hcmute.MobilePhoneShop.dtos.ProductDetailDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Product;
import com.hcmute.MobilePhoneShop.services.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.getProductDetail(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.create(productDTO),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable String id,@RequestBody ProductDTO productDTO){
        try{
            return ResponseEntity.ok(productService.update(id,productDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false, e.getMessage()));
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable String id){
        try {
            return ResponseEntity.ok(productService.delete(id));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse(false,e.getMessage()));
        }
    }
}
