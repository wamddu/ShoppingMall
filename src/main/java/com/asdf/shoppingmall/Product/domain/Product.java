package com.asdf.shoppingmall.Product.domain;

import com.asdf.shoppingmall.User.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private int stockQuantity;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    public void getDecreaseStockQuantity(int quantity) {
        if(stockQuantity < quantity) {
            throw new IllegalArgumentException("재고 부족");
        }

        this.stockQuantity -= quantity;
    }
}
