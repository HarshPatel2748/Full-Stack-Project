package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.OrderDTO;
import com.marketplace.marketplace_backend.entity.*;
import com.marketplace.marketplace_backend.repository.OrderItemRepository;
import com.marketplace.marketplace_backend.repository.OrderRepository;
import com.marketplace.marketplace_backend.repository.ProductRepository;
import com.marketplace.marketplace_backend.repository.UserRepository;
import com.marketplace.marketplace_backend.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public Order placeOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //create order
        Order order = Order.builder()
                .user(user)
                .orderStatus(OrderStatus.valueOf("PENDING"))
                .totalAmount(0.0)
                .build();
        order = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for(int i=0; i < orderDTO.getProductIds().size(); i++){
            //fetch product
            Long productId = orderDTO.getProductIds().get(i);
            Integer quantity = orderDTO.getQuantities().get(i);

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found" + productId));

            //check stock
            if (product.getStock() < quantity){
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            //reduce product stock
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);

            double itemTotal = product.getPrice() * quantity;
            totalAmount += itemTotal;

            //create order item
            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(quantity)
                    .price(product.getPrice())
                    .build();
            orderItems.add(item);
        }

        //save order items
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }


    //get all orders for a user
    @Override
    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return  orderRepository.findByUser(user);
    }

    //get all orders
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    //update order status
    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        try{
            OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
            order.setOrderStatus(orderStatus);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Invalid order status: " + status);
        }

        return orderRepository.save(order);
    }

    //get Order by Id
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
