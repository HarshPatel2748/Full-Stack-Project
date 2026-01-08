package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.OrderDTO;
import com.marketplace.marketplace_backend.entity.Order;

import java.util.List;

public interface OrderService {

    //place a new order
    Order placeOrder(OrderDTO orderDTO);

    //get all orders for a user
    List<Order> getOrdersByUser(Long userId);

    //get all orders
    List<Order> getAllOrders();

    //update order status
    Order updateOrderStatus(Long orderId, String status);

    //get Order by Id
    Order getOrderById(Long orderId);
}
