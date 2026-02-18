package com.asdf.shoppingmall.Order.service;

import com.asdf.shoppingmall.Order.domain.Delivery;
import com.asdf.shoppingmall.Order.domain.DeliveryStatus;
import com.asdf.shoppingmall.Order.repository.DeliveryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public void startDelivery(Long deliveryId) {

        if(deliveryRepository.findById(deliveryId).isEmpty()) {
            throw new IllegalArgumentException("Delivery Not Found");
        }

        Delivery delivery = deliveryRepository.findById(deliveryId).get();
        delivery.setDeliveryStatus(DeliveryStatus.START);
        deliveryRepository.save(delivery);
    }

    @Transactional
    public void shippingDelivery(Long deliveryId) {

        if(deliveryRepository.findById(deliveryId).isEmpty()) {
            throw new IllegalArgumentException("Delivery Not Found");
        }

        Delivery delivery = deliveryRepository.findById(deliveryId).get();
        delivery.setDeliveryStatus(DeliveryStatus.SHIPPED);
        deliveryRepository.save(delivery);
    }

    @Transactional
    public void doneDelivery(Long deliveryId) {

        if(deliveryRepository.findById(deliveryId).isEmpty()) {
            throw new IllegalArgumentException("Delivery Not Found");
        }

        Delivery delivery = deliveryRepository.findById(deliveryId).get();
        delivery.setDeliveryStatus(DeliveryStatus.DELIVERED);
        deliveryRepository.save(delivery);
    }
}
