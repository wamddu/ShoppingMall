package com.asdf.shoppingmall.Order.controller;

import com.asdf.shoppingmall.Order.repository.OrderRepository;
import com.asdf.shoppingmall.Order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.asdf.shoppingmall.Order.domain.Order;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "내 주문 내역 조회")
    @GetMapping("/api/order/my")
    public ResponseEntity<List<Order>> getMyOrders() {
        try {
            List<Order> orders = orderService.getMyOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "주문 추가")
    @PostMapping("/api/order/add")
    public ResponseEntity<String> addOrder() {
        try {
            orderService.createOrder();

            return  new ResponseEntity<>("주문 성공!", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println( e.getMessage() );
            return new ResponseEntity<>("주문 실패!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "주문 취소")
    @PostMapping("/api/order/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        try {
            orderService.cancelOrder(id);

            return  new ResponseEntity<>("삭제 성공!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("삭제 실패!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
