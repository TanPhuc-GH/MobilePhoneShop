package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends MongoRepository<OrderDetail,String> {
}
