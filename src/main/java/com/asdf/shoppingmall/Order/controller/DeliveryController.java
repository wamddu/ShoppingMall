package com.asdf.shoppingmall.Order.controller;

import com.asdf.shoppingmall.Order.domain.DeliveryStatus;
import com.asdf.shoppingmall.Order.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

    @Autowired
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Operation(summary = "배달 시작")
    @PostMapping("/api/delivery/start/{id}")
    public ResponseEntity<String> startDelivery(@PathVariable Long id) {

        try {

            deliveryService.startDelivery(id);

            return new ResponseEntity<>("배달 시작", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("서버 오류!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "배송 상태 변경")
    @PostMapping("/api/delivery/change/{id}/{status}")
    public ResponseEntity<String> changeStatus(@PathVariable Long id, @PathVariable DeliveryStatus status) {

        try {
            deliveryService.changeStatus(id, status);

            return new ResponseEntity<>("배달 상태 변경 성공!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("배달 상태 변경 실패!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
