package com.uade.tpo.grupo3.amancay.service.orders;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.grupo3.amancay.entity.Order;
import com.uade.tpo.grupo3.amancay.entity.OrderItem;
import com.uade.tpo.grupo3.amancay.entity.Product;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderItemRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderItemResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderStatusUpdateRequest;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.repository.OrderRepository;
import com.uade.tpo.grupo3.amancay.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;

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
        order.setStatus(orderRequest.getStatus() != null ? orderRequest.getStatus() : "PENDING");
        order.setNotes(orderRequest.getNotes());
        order.setOrderDate(LocalDateTime.now());
        order.setUpdatedDate(LocalDateTime.now());
        order.setTotalAmount(0.0); // Inicializar totalAmount
        
        // Crear y agregar OrderItems
        if (orderRequest.getItems() != null && !orderRequest.getItems().isEmpty()) {
            for (OrderItemRequest itemRequest : orderRequest.getItems()) {
                Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found with id: " + itemRequest.getProductId()));
                
                order.addItem(product, itemRequest.getQuantity(), itemRequest.getUnitPrice());
            }
        }
        
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
        order.setStatus(orderRequest.getStatus());
        order.setNotes(orderRequest.getNotes());
        order.setUpdatedDate(LocalDateTime.now());

        // Actualizar OrderItems si se proporcionan
        if (orderRequest.getItems() != null && !orderRequest.getItems().isEmpty()) {
            // Limpiar items existentes
            order.getItems().clear();
            
            // Agregar nuevos items
            for (OrderItemRequest itemRequest : orderRequest.getItems()) {
                Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found with id: " + itemRequest.getProductId()));
                
                order.addItem(product, itemRequest.getQuantity(), itemRequest.getUnitPrice());
            }
        }

        orderRepository.save(order);
        return new GenericResponse(id, "Order updated successfully");
    }

    @Override
    public Page<Order> getOrdersByStatus(String status, PageRequest pageRequest) {
        return orderRepository.findByStatus(status, pageRequest);
    }

    @Override
    public Page<Order> getOrdersByCustomerId(Long customerId, PageRequest pageRequest) {
        return orderRepository.findByCustomerId(customerId, pageRequest);
    }

    @Override
    public Page<Order> getOrdersByProductId(Long productId, PageRequest pageRequest) {
        return orderRepository.findByItemsProductId(productId, pageRequest);
    }
    
    @Override
    public GenericResponse updateOrderStatus(Long id, OrderStatusUpdateRequest statusUpdate) throws InvalidParameterException {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isEmpty()) {
            throw new NotFoundException("Order not found with id: " + id);
        }

        Order order = existingOrder.get();
        order.setStatus(statusUpdate.getStatus());
        if (statusUpdate.getNotes() != null) {
            order.setNotes(statusUpdate.getNotes());
        }
        order.setUpdatedDate(LocalDateTime.now());

        orderRepository.save(order);
        return new GenericResponse(id, "Order status updated successfully");
    }
    
    @Override
    public OrderResponse convertToOrderResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
            .map(this::convertToOrderItemResponse)
            .collect(java.util.stream.Collectors.toList());
            
        return new OrderResponse(
            order.getId(),
            order.getCustomerId(),
            itemResponses,
            order.getStatus(),
            order.getTotalAmount(),
            order.getOrderDate(),
            order.getUpdatedDate(),
            order.getNotes()
        );
    }
    
    /**
     * Convierte una entidad OrderItem a OrderItemResponse
     */
    private OrderItemResponse convertToOrderItemResponse(OrderItem orderItem) {
        return new OrderItemResponse(
            orderItem.getId(),
            orderItem.getProduct().getId(),
            orderItem.getProduct().getName(),
            orderItem.getQuantity(),
            orderItem.getUnitPrice(),
            orderItem.getSubtotal()
        );
    }
}
