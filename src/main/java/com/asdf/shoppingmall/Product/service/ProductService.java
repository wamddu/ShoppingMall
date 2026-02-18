package com.asdf.shoppingmall.Product.service;

import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.Product.repository.ProductRepository;
import com.asdf.shoppingmall.User.domain.Role;
import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void addProduct(Product product) {

            if(productRepository.existsProductByName(product.getName())) {
                throw new IllegalArgumentException("Product already exists");
            }

            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            try{
                product.setSeller(user);
                productRepository.save(product);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Product already exists");
        }
    }

    public void deleteProduct(long Id) {

        Product product1 = productRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(product1.getSeller().getId().equals(user.getId()) || user.getRole() == Role.ADMIN) {
            productRepository.delete(product1);
        }
        else {
            throw new IllegalArgumentException("Only Admin or Seller can delete this product");
        }
    }
}
