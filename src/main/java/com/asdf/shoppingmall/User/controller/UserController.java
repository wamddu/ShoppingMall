package com.asdf.shoppingmall.User.controller;

import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.dto.LoginRequest;
import com.asdf.shoppingmall.User.dto.SignupRequest;
import com.asdf.shoppingmall.User.dto.UserResponseDto;
import com.asdf.shoppingmall.User.service.UserService;
import com.asdf.shoppingmall.security.Jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody SignupRequest request) {
        System.out.println("signup");
        try {
            userService.signUp(request);

            return ResponseEntity.ok("유저 생성 성공!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(token);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/api/user/delete/me")
    public ResponseEntity<?> deleteMyAccount() {
        try {
            userService.deleteMyAccount();

            return ResponseEntity.ok("삭제 성공!");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "회원 삭제")
    @DeleteMapping("/api/admin/delete/{id}")
    public ResponseEntity<?> deleteUserByAdmin(@PathVariable Long id) {
        try{
            userService.deleteUserByAdmin(id);

            return ResponseEntity.ok("삭제 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "모든 회원 조회")
    @GetMapping("/api/admin/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
