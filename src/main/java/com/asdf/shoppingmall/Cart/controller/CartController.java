package com.asdf.shoppingmall.Cart.controller;

import com.asdf.shoppingmall.Cart.domain.CartRequestDto;
import com.asdf.shoppingmall.Cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/api/cart/add")
    public ResponseEntity<String> addCart(@RequestBody CartRequestDto requestDto) {
        try {
            cartService.addCart(requestDto);

            return ResponseEntity.ok().body("카트에 담기 성공");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/api/cart/product/delete")
    public ResponseEntity<String> cartProductDelete(@RequestBody CartRequestDto requestDto) {
        try {
            cartService.removeCartProdcut(requestDto);

            return ResponseEntity.ok("장바구니 제품 삭제 성공!");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
