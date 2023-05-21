package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

}
