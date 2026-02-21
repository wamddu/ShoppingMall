package com.asdf.shoppingmall.Cart.controller;

import com.asdf.shoppingmall.Cart.domain.CartRequestDto;
import com.asdf.shoppingmall.Cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asdf.shoppingmall.Cart.domain.Cart;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "내 장바구니 조회")
    @GetMapping("/api/cart/my")
    public ResponseEntity<Cart> getMyCart() {
        try {
            Cart cart = cartService.getMyCart();
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "제품 장바구니 추가")
    @PostMapping("/api/cart/add")
    public ResponseEntity<String> addCart(@RequestBody CartRequestDto requestDto) {
        try {
            cartService.addCart(requestDto);

            return ResponseEntity.ok().body("카트에 담기 성공");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "장바구니에서 해당 제품 삭제")
    @DeleteMapping("/api/cart/product/delete")
    public ResponseEntity<String> cartProductDelete(@RequestBody CartRequestDto requestDto) {
        try {
            cartService.removeCartProdcut(requestDto);

            return ResponseEntity.ok("장바구니 제품 삭제 성공!");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "장바구니 제품 수량 변경")
    @PostMapping("/api/cart/product/change")
    public ResponseEntity<String> cartProductChange(@RequestBody CartRequestDto requestDto) {

        try {
            cartService.changeCartProductCount(requestDto);

            return ResponseEntity.ok().body("수량 변경 성공!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
