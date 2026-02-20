package com.asdf.shoppingmall.Cart.service;

import com.asdf.shoppingmall.Cart.domain.Cart;
import com.asdf.shoppingmall.Cart.domain.CartRequestDto;
import com.asdf.shoppingmall.Cart.repository.CartRepository;
import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.Product.dto.ProductRequestDto;
import com.asdf.shoppingmall.Product.repository.ProductRepository;
import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public void addCart(CartRequestDto requestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));


        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Cart cart = user.getCart();
        cart.addCartProduct(product, requestDto.getCount());
        cartRepository.save(cart);
    }

    public void removeCartProdcut(CartRequestDto requestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user =  userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Cart cart = user.getCart();
        cart.removeCartProduct(product);
        cartRepository.save(cart);
    }

    public void changeCartProductCount(CartRequestDto requestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        Product product  = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Cart cart = user.getCart();
        cart.changeCount(product, requestDto.getCount());
        cartRepository.save(cart);
    }
}
