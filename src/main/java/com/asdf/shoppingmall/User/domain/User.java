package com.asdf.shoppingmall.User.domain;

import com.asdf.shoppingmall.Cart.domain.Cart;
import com.asdf.shoppingmall.Global.Address;
import com.asdf.shoppingmall.Order.domain.Order;
import com.asdf.shoppingmall.Product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<Product>();
}
