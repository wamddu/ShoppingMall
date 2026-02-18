package com.asdf.shoppingmall.Cart.domain;

import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.User.domain.User;
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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void addCartProduct(Product product, int count) {
        Cart_Product cartProduct = findCartProduct(product);

        if(cartProduct == null) {
            cartProduct = Cart_Product.createCartProduct(product, count);
            cartProduct.setCart(this);
            cartProducts.add(cartProduct);
            return;
        }

        else {
            cartProduct.addCount(count);
            return;
        }
    }

    public void changeCount(Product product, int count) {
        Cart_Product cartProduct = findCartProduct(product);

        if(cartProduct == null) {
            throw new IllegalArgumentException("Product doesn't exist");
        }

        cartProduct.changeCount(count);
    }

    public void removeCartProduct(Product product) {
        Cart_Product cartProduct = findCartProduct(product);

        if(cartProduct == null) {
            throw new IllegalArgumentException("Product doesn't exist");
        }

        cartProducts.remove(cartProduct);
        cartProduct.setCart(null);
    }

    private Cart_Product findCartProduct(Product product) {
        return cartProducts.stream()
                .filter(cp -> cp.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }
}
