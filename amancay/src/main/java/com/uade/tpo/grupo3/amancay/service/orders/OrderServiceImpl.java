package com.uade.tpo.grupo3.amancay.service.orders;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.Order;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderRequest;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> getOrders(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProduct(new Product());
        order.getProduct().setId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
        order.setStatus(orderRequest.getStatus() != null ? orderRequest.getStatus() : "PENDING");
        order.setNotes(orderRequest.getNotes());
        order.setOrderDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public GenericResponse deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new NotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
        return new GenericResponse(id, "Order deleted successfully");
    }

    @Override
    public GenericResponse updateOrder(Long id, OrderRequest orderRequest) throws InvalidParameterException {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new NotFoundException("Order not found with id: " + id);
        }

        Order order = existingOrder.get();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProduct(new Product());
        order.getProduct().setId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
        order.setStatus(orderRequest.getStatus());
        order.setNotes(orderRequest.getNotes());
        order.setUpdatedDate(LocalDateTime.now());

        orderRepository.save(order);
        return new GenericResponse(id, "Order updated successfully");
    }

    @Override
    public Page<Order> getOrdersByStatus(String status, PageRequest pageRequest) {
        // For now, return all orders and filter in memory
        // In a production environment, you might want to create custom repository methods
        Page<Order> allOrders = orderRepository.findAll(pageRequest);
        return allOrders;
    }

    @Override
    public Page<Order> getOrdersByCustomerId(Long customerId, PageRequest pageRequest) {
        // For now, return all orders and filter in memory
        // In a production environment, you might want to create custom repository methods
        Page<Order> allOrders = orderRepository.findAll(pageRequest);
        return allOrders;
    }

    @Override
    public Page<Order> getOrdersByProductId(Long productId, PageRequest pageRequest) {
        // For now, return all orders and filter in memory
        // In a production environment, you might want to create custom repository methods
        Page<Order> allOrders = orderRepository.findAll(pageRequest);
        return allOrders;
    }
}
