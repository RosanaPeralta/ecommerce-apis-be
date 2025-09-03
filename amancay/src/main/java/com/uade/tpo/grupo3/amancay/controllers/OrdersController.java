package com.uade.tpo.grupo3.amancay.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uade.tpo.grupo3.amancay.service.orders.*;
import com.uade.tpo.grupo3.amancay.entity.Order;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderStatusUpdateRequest;
import com.uade.tpo.grupo3.amancay.exceptions.NotFoundException;
import com.uade.tpo.grupo3.amancay.exceptions.InvalidParameters;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("orders")
public class OrdersController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<Order> orders;
        if (page == null || size == null)
            orders = orderService.getOrders(PageRequest.of(0, Integer.MAX_VALUE));
        else
            orders = orderService.getOrders(PageRequest.of(page, size));
        
        List<OrderResponse> orderResponses = orders.getContent().stream()
            .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
            .collect(java.util.stream.Collectors.toList());
        
        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) throws NotFoundException {
        Optional<Order> result = orderService.getOrderById(orderId);
        if(result.isPresent()) {
            OrderResponse orderResponse = ((OrderServiceImpl) orderService).convertToOrderResponse(result.get());
            return ResponseEntity.ok(orderResponse);
        }
        
        throw new NotFoundException("Order not found with id: " + orderId);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) throws NotFoundException, InvalidParameters {
        Order result = orderService.createOrder(request);
        OrderResponse orderResponse = ((OrderServiceImpl) orderService).convertToOrderResponse(result);
        return ResponseEntity.created(URI.create("/orders/" + result.getId())).body(orderResponse);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<GenericResponse> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest request) throws NotFoundException, InvalidParameters {
        GenericResponse result = orderService.updateOrder(orderId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<GenericResponse> deleteOrder(@PathVariable Long orderId) throws NotFoundException, InvalidParameters {
        GenericResponse result = orderService.deleteOrder(orderId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(
            @PathVariable String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<Order> orders;
        if (page == null || size == null)
            orders = orderService.getOrdersByStatus(status, PageRequest.of(0, Integer.MAX_VALUE));
        else
            orders = orderService.getOrdersByStatus(status, PageRequest.of(page, size));
        
        // Convertir a OrderResponse para incluir OrderItems
        List<OrderResponse> orderResponses = orders.getContent().stream()
            .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
            .collect(java.util.stream.Collectors.toList());
        
        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<Order> orders;
        if (page == null || size == null)
            orders = orderService.getOrdersByCustomerId(customerId, PageRequest.of(0, Integer.MAX_VALUE));
        else
            orders = orderService.getOrdersByCustomerId(customerId, PageRequest.of(page, size));
        
        // Convertir a OrderResponse para incluir OrderItems
        List<OrderResponse> orderResponses = orders.getContent().stream()
            .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
            .collect(java.util.stream.Collectors.toList());
        
        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByProductId(
            @PathVariable Long productId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        Page<Order> orders;
        if (page == null || size == null)
            orders = orderService.getOrdersByProductId(productId, PageRequest.of(0, Integer.MAX_VALUE));
        else
            orders = orderService.getOrdersByProductId(productId, PageRequest.of(page, size));
        
        // Convertir a OrderResponse para incluir OrderItems
        List<OrderResponse> orderResponses = orders.getContent().stream()
            .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
            .collect(java.util.stream.Collectors.toList());
        
        return ResponseEntity.ok(orderResponses);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<GenericResponse> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatusUpdateRequest request) throws NotFoundException, InvalidParameters {
        GenericResponse result = orderService.updateOrderStatus(orderId, request);
        return ResponseEntity.ok(result);
    }
}