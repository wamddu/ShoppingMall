package com.asdf.shoppingmall.User.service;

import com.asdf.shoppingmall.User.domain.Role;
import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.dto.SignupRequest;
import com.asdf.shoppingmall.User.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> signUp(SignupRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user =  new User();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        user.setAddress(request.getAddress());
        user.setName(request.getName());
        userRepository.save(user);

        return ResponseEntity.ok("Sign up successful");
    }
}
