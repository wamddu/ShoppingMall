package com.asdf.shoppingmall.Product.repository;

import com.asdf.shoppingmall.Product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsProductByName(String name);
    Optional<Product> findByName(String name);
}
