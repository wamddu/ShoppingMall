package com.asdf.shoppingmall.Product.controller;

import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.Product.dto.ProductRequestDto;
import com.asdf.shoppingmall.Product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "제품 등록")
    @PostMapping("/api/product/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDto requestDto) {

        try{
            productService.addProduct(requestDto);

            return ResponseEntity.ok("상품 등록 성공!");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "제품 삭제")
    @DeleteMapping("/api/product/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {

        try {
            productService.deleteProduct(id);

            return ResponseEntity.ok("삭제 성공!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Operation(summary = "모든 제품 조회")
    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

}
