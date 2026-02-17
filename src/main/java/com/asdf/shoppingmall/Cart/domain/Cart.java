package com.asdf.shoppingmall.Cart.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;

    private Date cartDate;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Cart_Product> cartProducts = new ArrayList<Cart_Product>();

    public void addCartProduct(Cart_Product cartProduct) {
        cartProducts.add(cartProduct);
        cartProduct.setCart(this);
    }
}
