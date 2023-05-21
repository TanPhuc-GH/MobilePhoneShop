package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    @Query("{'sold_out': false, 'disable': false}")
    Page<Product> findBySold_outIsFalse(Pageable pageable);
    @Query("{'productName': { $regex: ?0, $options: 'i' }}")
    Page<Product> searchByProductName(String keyword, Pageable pageable);
    @Query("{$or: [{'brandId': ?0}, {'categoriesId': ?0}]}")
    Page<Product> filter(String filter,Pageable pageable);
}
