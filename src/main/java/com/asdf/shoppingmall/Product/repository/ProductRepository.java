package com.asdf.shoppingmall.Product.repository;

import com.asdf.shoppingmall.Product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> existsByName(String name);
}
