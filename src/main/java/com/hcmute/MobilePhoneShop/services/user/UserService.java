package com.hcmute.MobilePhoneShop.services.user;

import com.hcmute.MobilePhoneShop.dtos.UserDTO;
import com.hcmute.MobilePhoneShop.entities.User;
import org.bson.types.ObjectId;

public interface UserService {
    User getUser(String id);
    User create(UserDTO userDTO);
    User update(String id, UserDTO userDTO);
    User delete(String id);
}
