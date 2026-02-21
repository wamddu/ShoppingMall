package com.asdf.shoppingmall.Cart.domain;

import com.asdf.shoppingmall.Product.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart_Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_PRODUCT_ID")
    private Long id;

    int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    public static Cart_Product createCartProduct(Product product, int count) {
        Cart_Product cp = new Cart_Product();
        cp.setProduct(product);
        cp.setCount(count);
        return cp;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void changeCount(int count) {
        this.count = count;
    }
}
