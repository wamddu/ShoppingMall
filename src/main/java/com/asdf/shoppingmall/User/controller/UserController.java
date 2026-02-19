package com.asdf.shoppingmall.User.controller;

import com.asdf.shoppingmall.User.dto.LoginRequest;
import com.asdf.shoppingmall.User.dto.SignupRequest;
import com.asdf.shoppingmall.User.service.UserService;
import com.asdf.shoppingmall.security.Jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody SignupRequest request) {
        try {
            userService.signUp(request);

            return ResponseEntity.ok("유저 생성 성공!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(token);
    }

    @DeleteMapping({"/api/user/delete" , "/api/admin/delete"})
    public ResponseEntity<?> deleteUser(@RequestBody String username) {
        try {
            userService.deleteUser(username);

            return ResponseEntity.ok("삭제 성공!");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
