package com.asdf.shoppingmall.Order.service;

import com.asdf.shoppingmall.Cart.domain.Cart;
import com.asdf.shoppingmall.Cart.domain.Cart_Product;
import com.asdf.shoppingmall.Cart.repository.CartRepository;
import com.asdf.shoppingmall.Order.domain.Delivery;
import com.asdf.shoppingmall.Order.domain.DeliveryStatus;
import com.asdf.shoppingmall.Order.domain.Order;
import com.asdf.shoppingmall.Order.domain.Order_Product;
import com.asdf.shoppingmall.Order.repository.OrderRepository;
import com.asdf.shoppingmall.Product.domain.Product;
import com.asdf.shoppingmall.User.domain.User;
import com.asdf.shoppingmall.User.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Long createOrder(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Cart cart = cartRepository.findById(user.getCart().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        Order order = new Order();

        for(Cart_Product cartProduct : cart.getCartProducts()) {
            Product product = cartProduct.getProduct();
            int count = cartProduct.getCount();

            product.getDecreaseStockQuantity(count);

            Order_Product orderProduct = new Order_Product();
            orderProduct.setProduct(product);
            orderProduct.setCount(count);
            orderProduct.setOrderPrice(product.getPrice());

            order.addOrderProduct(orderProduct);
        }

        Delivery delivery = new Delivery();
        delivery.setDeliveryAddress(user.getAddress());
        delivery.setDeliveryDate(new Date());
        delivery.setDeliveryStatus(DeliveryStatus.READY);
        order.setDelivery(delivery);

        orderRepository.save(order);

        cartRepository.deleteById(user.getCart().getId());

        return order.getId();
    }
}
