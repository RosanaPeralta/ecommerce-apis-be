package com.uade.tpo.grupo3.amancay.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
import java.security.InvalidParameterException;

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
        try {
            Page<Order> orders;
            if (page == null || size == null)
                orders = orderService.getOrders(PageRequest.of(0, Integer.MAX_VALUE));
            else
                orders = orderService.getOrders(PageRequest.of(page, size));
            
            List<OrderResponse> orderResponses = orders.getContent().stream()
                .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
                .collect(java.util.stream.Collectors.toList());
            
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        try {
            Optional<Order> result = orderService.getOrderById(orderId);
            if(result.isPresent()) {
                OrderResponse orderResponse = ((OrderServiceImpl) orderService).convertToOrderResponse(result.get());
                return ResponseEntity.ok(orderResponse);
            }
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        try {
            Order result = orderService.createOrder(request);
            OrderResponse orderResponse = ((OrderServiceImpl) orderService).convertToOrderResponse(result);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .location(URI.create("/orders/" + result.getId()))
                    .body(orderResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<GenericResponse> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest request) {
        try {
            GenericResponse result = orderService.updateOrder(orderId, request);
            return ResponseEntity.ok(result);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<GenericResponse> deleteOrder(@PathVariable Long orderId) {
        try {
            GenericResponse result = orderService.deleteOrder(orderId);
            return ResponseEntity.ok(result);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(
            @PathVariable String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            Page<Order> orders;
            if (page == null || size == null)
                orders = orderService.getOrdersByStatus(status, PageRequest.of(0, Integer.MAX_VALUE));
            else
                orders = orderService.getOrdersByStatus(status, PageRequest.of(page, size));
            List<OrderResponse> orderResponses = orders.getContent().stream()
                .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
                .collect(java.util.stream.Collectors.toList());
            
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            Page<Order> orders;
            if (page == null || size == null)
                orders = orderService.getOrdersByCustomerId(customerId, PageRequest.of(0, Integer.MAX_VALUE));
            else
                orders = orderService.getOrdersByCustomerId(customerId, PageRequest.of(page, size));
            List<OrderResponse> orderResponses = orders.getContent().stream()
                .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
                .collect(java.util.stream.Collectors.toList());
            
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByProductId(
            @PathVariable Long productId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            Page<Order> orders;
            if (page == null || size == null)
                orders = orderService.getOrdersByProductId(productId, PageRequest.of(0, Integer.MAX_VALUE));
            else
                orders = orderService.getOrdersByProductId(productId, PageRequest.of(page, size));
            List<OrderResponse> orderResponses = orders.getContent().stream()
                .map(order -> ((OrderServiceImpl) orderService).convertToOrderResponse(order))
                .collect(java.util.stream.Collectors.toList());
            
            return ResponseEntity.ok(orderResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<GenericResponse> updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatusUpdateRequest request) {
        try {
            GenericResponse result = orderService.updateOrderStatus(orderId, request);
            return ResponseEntity.ok(result);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (InvalidParameterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}