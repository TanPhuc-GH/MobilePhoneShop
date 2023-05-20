package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

    @Query("{'sold_out': false, 'disable': false}")
    List<Product> findBySold_outIsFalse();
}
