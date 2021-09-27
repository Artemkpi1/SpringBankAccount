package com.example.bankAccountProject;

import com.example.bankAccountProject.DAORepository.UserRepository;
import com.example.bankAccountProject.model.Role;
import com.example.bankAccountProject.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class BankAccountProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountProjectApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner runApplication(UserRepository userRepository) {
//		return (args -> {
//			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//			Random random = new Random();
//
//			List<User> users = new ArrayList<>();
//
//			User user = User.builder()
//					.firstName("Artem")
//					.lastName("Zhuravskyi")
//					.email("a")
//					.password(bCryptPasswordEncoder.encode("a"))
//					.role(Role.ROLE_ADMIN)
//					.build();
//
//			users.add(user);
//			userRepository.saveAll(users);
//
//		});
//	}
}
