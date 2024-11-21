package com.example.loginexemplr;

import com.example.loginexemplr.models.User;
import com.example.loginexemplr.payload.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class LoginExemplrApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginExemplrApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            Optional<User> user = userRepository.findByUsername("admin");
            if (user == null){
            userRepository.save(new User("admin","admin@gmail.com","ADMIN"));}};
        };
    }

