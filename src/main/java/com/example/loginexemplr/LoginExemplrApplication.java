package com.example.loginexemplr;

import com.example.loginexemplr.controllers.TestController;
import com.example.loginexemplr.models.Role;
import com.example.loginexemplr.models.User;
import com.example.loginexemplr.payload.repository.RoleRepository;
import com.example.loginexemplr.payload.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.example.loginexemplr.models.ERole.*;

@SpringBootApplication
public class LoginExemplrApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginExemplrApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository) {
        Logger logger = LoggerFactory.getLogger(TestController.class);
        return args -> {
            if (roleRepository.findByName(ROLE_ADMIN).isEmpty()) {
                roleRepository.save(new Role(ROLE_ADMIN));
                Optional<Role> role = roleRepository.findByName(ROLE_ADMIN);
                role.ifPresent(value -> logger.info(value.getId() + " " + value.getName()));
            }
            if (roleRepository.findByName(ROLE_MODERATOR).isEmpty()) {
                roleRepository.save(new Role(ROLE_MODERATOR));
                Optional<Role> role = roleRepository.findByName((ROLE_MODERATOR));
                role.ifPresent(value -> logger.info(value.getId() + " " + value.getName()));
            }
            if (roleRepository.findByName(ROLE_USER).isEmpty()) {
                roleRepository.save(new Role(ROLE_USER));
                Optional<Role> role = roleRepository.findByName(ROLE_USER);
                role.ifPresent(value -> logger.info(value.getId() + " " + value.getName()));
            }
            if (!userRepository.existsByUsername("admin")) {
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName(ROLE_ADMIN).get());
                roles.add(roleRepository.findByName(ROLE_MODERATOR).get());
                User admin = new User("admin", "admin@gmail.com", "admin");
                admin.setRoles(roles);
                userRepository.save(admin);
                logger.info("Admin created");
            }
            if (!userRepository.existsByUsername("moderator")) {
                User moderator = new User("moderator", "moderator@gmail.com", "moderator");
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName(ROLE_MODERATOR).get());
                roles.add(roleRepository.findByName(ROLE_USER).get());
                moderator.setRoles(roles);
                userRepository.save(moderator);
                logger.info("moderator created");
            }
            if (!userRepository.existsByUsername("simpleUser")) {
                User simpleUser = new User("simpleUser", "simpleUser@gmail.com", "simpleUser");
                Set<Role> roles = new HashSet<>();
                roles.add(roleRepository.findByName(ROLE_USER).get());
                simpleUser.setRoles(roles);
                userRepository.save(simpleUser);
                logger.info("simpleUser created");
            }
        };
    }
}







