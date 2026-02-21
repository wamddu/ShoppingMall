package com.asdf.shoppingmall.Order.repository;

import com.asdf.shoppingmall.Order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asdf.shoppingmall.User.domain.User;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByOrderDateDesc(User user);
}

