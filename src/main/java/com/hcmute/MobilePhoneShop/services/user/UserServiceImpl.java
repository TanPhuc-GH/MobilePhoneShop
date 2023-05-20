package com.hcmute.MobilePhoneShop.services.user;

import com.hcmute.MobilePhoneShop.dtos.UserDTO;
import com.hcmute.MobilePhoneShop.entities.User;
import com.hcmute.MobilePhoneShop.exceptions.InvalidException;
import com.hcmute.MobilePhoneShop.exceptions.NotFoundException;
import com.hcmute.MobilePhoneShop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public User create(UserDTO userDTO) {

        if (ObjectUtils.isEmpty(userDTO.getName())){
            throw new InvalidException("Tên người dùng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getAddress())){
            throw new InvalidException("Địa chỉ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getEmail())){
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getPhoneNumber())){
            throw new InvalidException("Số điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getPassword())){
            throw new InvalidException("Mật khẩu không đươc bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getRoles())){
            throw new InvalidException("Role không được bỏ trống");
        }
        if (userRepository.existsByEmail(userDTO.getEmail().trim())){
            throw new InvalidException(String.format("Email: %s đã tồn tại", userDTO.getEmail()));
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail().trim());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        userRepository.save(user);

        return user;
    }

    @Override
    public User update(String id, UserDTO userDTO) {
        User user = getUser(id);
        if (ObjectUtils.isEmpty(userDTO.getName())){
            throw new InvalidException("Tên người dùng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getAddress())){
            throw new InvalidException("Địa chỉ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getEmail())){
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getPhoneNumber())){
            throw new InvalidException("Số điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getPassword())){
            throw new InvalidException("Mật khẩu không đươc bỏ trống");
        }
        if (ObjectUtils.isEmpty(userDTO.getRoles())){
            throw new InvalidException("Role không được bỏ trống");
        }
        if (!user.getEmail().equalsIgnoreCase(userDTO.getEmail().trim()) && userRepository.existsByEmail(userDTO.getEmail().trim())){
            throw new InvalidException(String.format("Email: %s đã tồn tại", userDTO.getEmail()));
        }

        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail().trim());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        userRepository.save(user);
        return user;
    }

    @Override
    public User delete(String id) {
        User user = getUser(id);
        userRepository.delete(user);
        return null;
    }
}
