package com.hcmute.MobilePhoneShop.controllers;

import com.hcmute.MobilePhoneShop.dtos.ProductDTO;
import com.hcmute.MobilePhoneShop.dtos.ProductDetailDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Product;
import com.hcmute.MobilePhoneShop.services.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Product>> filter(@RequestParam String filter,
                                                @RequestParam int page,
                                                @RequestParam int size){
        return new ResponseEntity<>(productService.filter(filter, page, size),HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Product>> search(@RequestParam String keyword,
                                                @RequestParam int page,
                                                @RequestParam int size){
        return new ResponseEntity<>(productService.search(keyword, page, size),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam int page,
                                                       @RequestParam int size){
        return new ResponseEntity<>(productService.getAllProduct(page, size),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable String id){
        return new ResponseEntity<>(productService.getProductDetail(id), HttpStatus.OK);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(productService.create(productDTO),HttpStatus.OK);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
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
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
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
