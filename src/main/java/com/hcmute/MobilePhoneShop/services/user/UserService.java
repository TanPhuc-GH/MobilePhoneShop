package com.hcmute.MobilePhoneShop.services.user;

import com.hcmute.MobilePhoneShop.dtos.UserDTO;
import com.hcmute.MobilePhoneShop.entities.User;

import java.security.Principal;

public interface UserService {
    User getUser(String id);
    User create(UserDTO userDTO, Principal principal);
    User update(String id, UserDTO userDTO, Principal principal);
    User delete(String id);
}
