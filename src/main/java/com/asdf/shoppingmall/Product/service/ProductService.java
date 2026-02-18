package com.asdf.shoppingmall.Product.service;

import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.Product.repository.ProductRepository;
import com.asdf.shoppingmall.User.domain.Role;
import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.repository.UserRepository;
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

        if(productRepository.existsByName(product.getName()).isPresent()) {
            throw new IllegalArgumentException("Product already exists");
        }

        try {
            productRepository.save(product);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Product already exists");
        }
    }

    public void deleteProduct(Product product, Long userId) {
        if(productRepository.existsByName(product.getName()).isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        User user = userRepository.findById(userId).get();

        if(product.getSeller().getId().equals(user.getId()) || user.getRole() == Role.ADMIN) {
            productRepository.delete(product);
        }
        else {
            throw new IllegalArgumentException("Only Admin or Seller can delete this product");
        }
    }
}
