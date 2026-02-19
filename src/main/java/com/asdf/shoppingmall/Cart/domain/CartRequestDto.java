package com.asdf.shoppingmall.Cart.domain;

import lombok.Getter;

@Getter
public class CartRequestDto {

    private Long productId;
    private int count;
}
