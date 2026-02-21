package com.asdf.shoppingmall.Cart.repository;

import com.asdf.shoppingmall.Cart.domain.Cart;
import com.asdf.shoppingmall.Cart.domain.Cart_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<Cart_Product, Long> {
    void deleteByCart(Cart cart);
}
