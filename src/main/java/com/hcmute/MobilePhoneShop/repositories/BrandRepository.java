package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<Brand,String> {
    @Query("{'brandName': ?0}")
    boolean existsByBrandName(String brandName);
}
