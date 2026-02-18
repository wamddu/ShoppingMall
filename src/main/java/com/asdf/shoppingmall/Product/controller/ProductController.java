package com.asdf.shoppingmall.Product.controller;

import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.Product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    @PostMapping("/api/product/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {

        try{
            productService.addProduct(product);

            return ResponseEntity.ok("상품 등록 성공!");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product, @RequestBody Long id) {

        try {
            productService.deleteProduct(product, id);

            return ResponseEntity.ok("삭제 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
