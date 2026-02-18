package com.asdf.shoppingmall.User.controller;

import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            userService.addUser(user);

            return ResponseEntity.ok("유저 생성 성공!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
