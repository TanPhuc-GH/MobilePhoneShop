package com.hcmute.MobilePhoneShop;

import com.hcmute.MobilePhoneShop.entities.User;
import com.hcmute.MobilePhoneShop.repositories.UserRepository;
import com.hcmute.MobilePhoneShop.utils.EnumRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class MobilePhoneShopApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(MobilePhoneShopApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0){
			User user = new User("Tan Phuc", "0123456789", "Linh Trung, Thu Duc", "truongtanphuc2002@gmail.com","123456", Arrays.asList(EnumRoles.ADMIN.name()));
			userRepository.save(user);
		}
	}
}
