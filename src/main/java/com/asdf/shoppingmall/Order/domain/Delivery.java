package com.asdf.shoppingmall.Order.domain;

import com.asdf.shoppingmall.Global.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    private Date deliveryDate;

    @Embedded
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
}
