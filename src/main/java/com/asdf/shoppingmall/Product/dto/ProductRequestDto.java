package com.asdf.shoppingmall.Product.dto;

import lombok.Getter;

@Getter
public class ProductRequestDto {

    private String name;
    private int stockQuantity;
    private int price;
}
