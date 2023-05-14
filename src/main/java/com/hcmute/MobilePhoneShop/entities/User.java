package com.hcmute.MobilePhoneShop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private  String id;

    private String name;

    private String phoneNumber;

    private String address;
    @Indexed(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    private Boolean status = true;

    private List<String> roles = new ArrayList<>();

    public User(String name, String phoneNumber, String address, String email,
                String password, List<String> roles) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
