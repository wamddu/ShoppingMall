package com.asdf.shoppingmall.Order.repository;

import com.asdf.shoppingmall.Order.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
