package com.asdf.shoppingmall.User.service;

import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalArgumentException("username already exists");
        }

        userRepository.save(user);
    }
}
