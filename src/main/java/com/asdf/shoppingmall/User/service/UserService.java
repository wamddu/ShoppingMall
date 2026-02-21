package com.asdf.shoppingmall.User.service;

import com.asdf.shoppingmall.Cart.domain.Cart;
import com.asdf.shoppingmall.Cart.repository.CartRepository;
import com.asdf.shoppingmall.User.domain.Role;
import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.dto.UserResponseDto;
import com.asdf.shoppingmall.User.dto.SignupRequest;
import com.asdf.shoppingmall.User.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
    }

    public ResponseEntity<String> signUp(SignupRequest request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user =  new User();
        Cart cart = new Cart();
        user.setUsername(request.getUsername());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        user.setAddress(request.getAddress());
        user.setName(request.getName());
        user.setCart(cart);
        userRepository.save(user);
        cart.setUser(user);
        cartRepository.save(cart);

        return ResponseEntity.ok("Sign up successful");
    }

    public ResponseEntity<String> deleteMyAccount() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));

        userRepository.delete(user);

        return  ResponseEntity.ok("삭제 성공!");
    }

    public ResponseEntity<String> deleteUserByAdmin(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("user not found"));

        userRepository.delete(user);

        return ResponseEntity.ok("삭제 성공!");
    }

    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map((user) -> new UserResponseDto(
                        user.getUsername(),
                        user.getName(),
                        user.getAddress()
                ))
                .toList();
    }
}
