package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.Categories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends MongoRepository<Categories,String> {

}
