package com.asdf.shoppingmall.User.domain;

import com.asdf.shoppingmall.Cart.domain.Cart;
import com.asdf.shoppingmall.Global.Address;
import com.asdf.shoppingmall.Order.domain.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false, updatable = false)
    private Long id;

    private String username;
    private String password;
    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
