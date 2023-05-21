package com.hcmute.MobilePhoneShop.repositories;

import com.hcmute.MobilePhoneShop.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query(value = "{'email': ?0}", exists = true)
    boolean existsByEmail(String email);

    @Query(value = "{'email':  ?0}")
    Optional<User> getUserByEmail(String email);
}
