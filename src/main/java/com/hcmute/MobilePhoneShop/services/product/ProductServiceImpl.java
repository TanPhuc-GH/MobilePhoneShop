package com.hcmute.MobilePhoneShop.services.product;

import com.hcmute.MobilePhoneShop.dtos.ProductDTO;
import com.hcmute.MobilePhoneShop.dtos.ProductDetailDTO;
import com.hcmute.MobilePhoneShop.dtos.response.BaseResponse;
import com.hcmute.MobilePhoneShop.entities.Brand;
import com.hcmute.MobilePhoneShop.entities.Categories;
import com.hcmute.MobilePhoneShop.entities.Product;
import com.hcmute.MobilePhoneShop.entities.embedded.ProductPrice;
import com.hcmute.MobilePhoneShop.exceptions.InvalidException;
import com.hcmute.MobilePhoneShop.exceptions.NotFoundException;
import com.hcmute.MobilePhoneShop.repositories.BrandRepository;
import com.hcmute.MobilePhoneShop.repositories.CategoriesRepository;
import com.hcmute.MobilePhoneShop.repositories.ProductRepository;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public Page<Product> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findBySold_outIsFalse(pageable);
    }

    @Override
    public ProductDetailDTO getProductDetail(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product with %s not found", id)));
        Brand brand =  brandRepository.findById(product.getBrandId()).orElseThrow(() -> new NotFoundException(String.format("Brand with %s not found", id)));
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(product, brand.getBrandName());
        return productDetailDTO;
    }

    @Override
    public Product create(ProductDTO productDTO) {
        boolean existBrand = brandRepository.existsById(productDTO.getBandId());
        boolean existCategories = categoriesRepository.existsById(productDTO.getCategoriesId());
        if (ObjectUtils.isEmpty(productDTO.getProductName())){
            throw new InvalidException("Tên sản phẩm không được để trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getQuantity())){
            throw new InvalidException("Số lượng sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getDescription())){
            throw new InvalidException("Mô tả không được để trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getStorages())){
            throw new InvalidException("Dung lượng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getQuantityOfColors())){
            throw new InvalidException("Số lượng từng sản phẩm có màu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getCategoriesId())){
            throw new InvalidException("Mã mục không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getBandId())){
            throw new InvalidException("Mã thương hiệu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getColor())){
            throw new InvalidException("Màu sắc không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getPrice())){
            throw new InvalidException("Giá không được để trống");
        }
        if (!existBrand){
            throw new InvalidException(String.format("Mã thương hiệu %s không tồn tại",productDTO.getBandId()));
        }
        if (!existCategories){
            throw new InvalidException(String.format("Mã danh mục %s không tồn tại",productDTO.getCategoriesId()));
        }
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        product.setStorages(productDTO.getStorages());
        product.setQuantityOfColors(productDTO.getQuantityOfColors());
        product.setCategoriesId(productDTO.getCategoriesId());
        product.setBrandId(productDTO.getBandId());
        product.setColor(productDTO.getColor());
        product.setPrice(productDTO.getPrice());
        product.setDisable(productDTO.getDisable());
        productRepository.save(product);

        return product;
    }

    @Override
    public BaseResponse update(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Product with id: %s not found", id)));
        boolean existBrand = brandRepository.existsById(productDTO.getBandId());
        boolean existCategories = categoriesRepository.existsById(productDTO.getCategoriesId());
        if (ObjectUtils.isEmpty(productDTO.getProductName())){
            throw new InvalidException("Tên sản phẩm không được để trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getQuantity())){
            throw new InvalidException("Số lượng sản phẩm không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getDescription())){
            throw new InvalidException("Mô tả không được để trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getStorages())){
            throw new InvalidException("Dung lượng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getQuantityOfColors())){
            throw new InvalidException("Số lượng từng sản phẩm có màu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getCategoriesId())){
            throw new InvalidException("Mã mục không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getBandId())){
            throw new InvalidException("Mã thương hiệu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getColor())){
            throw new InvalidException("Màu sắc không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(productDTO.getPrice())){
            throw new InvalidException("Giá không được để trống");
        }
        if (!product.getBrandId().equalsIgnoreCase(productDTO.getBandId()) && !existBrand){
            throw new InvalidException(String.format("Mã thương hiệu %s không tồn tại",productDTO.getBandId()));
        }
        if (!product.getCategoriesId().equalsIgnoreCase(productDTO.getCategoriesId()) && !existCategories){
            throw new InvalidException(String.format("Mã danh mục %s không tồn tại",productDTO.getCategoriesId()));
        }

        product.setProductName(productDTO.getProductName());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        product.setStorages(productDTO.getStorages());
        product.setQuantityOfColors(productDTO.getQuantityOfColors());
        product.setCategoriesId(productDTO.getCategoriesId());
        product.setBrandId(productDTO.getBandId());
        product.setColor(productDTO.getColor());
        product.setPrice(productDTO.getPrice());
        product.setDisable(productDTO.getDisable());
        productRepository.save(product);

        return new BaseResponse(true,"Update successful");
    }

    @Override
    public BaseResponse delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Product with id %s not found",id)));
        product.setDisable(true);
        productRepository.save(product);
        return new BaseResponse(true,"Delete successful");
    }

    @Override
    public Page<Product> search(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.searchByProductName(keyword,pageable);
    }

    @Override
    public Page<Product> filter(String filter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.filter(filter,pageable);
    }
}
