package com.hcmute.MobilePhoneShop.controllers;

import com.hcmute.MobilePhoneShop.dtos.UserDTO;
import com.hcmute.MobilePhoneShop.entities.User;
import com.hcmute.MobilePhoneShop.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDTO userDTO, Principal principal){
        return new ResponseEntity<>(userService.create(userDTO,principal), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id,@Valid @RequestBody UserDTO userDTO, Principal principal){
        return new ResponseEntity<>(userService.update(id,userDTO,principal),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id){
        return new ResponseEntity<>(userService.delete(id),HttpStatus.OK);
    }
}
