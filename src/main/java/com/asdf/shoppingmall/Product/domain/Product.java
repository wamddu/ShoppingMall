package com.asdf.shoppingmall.Product.domain;

import com.asdf.shoppingmall.User.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private int stockQuantity;
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonIgnore
    private User seller;

    public void getDecreaseStockQuantity(int quantity) {
        if(stockQuantity < quantity) {
            throw new IllegalArgumentException("재고 부족");
        }

        this.stockQuantity -= quantity;
    }
}
