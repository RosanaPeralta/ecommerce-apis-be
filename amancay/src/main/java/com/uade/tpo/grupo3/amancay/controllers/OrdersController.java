package com.uade.tpo.grupo3.amancay.controllers;

import java.net.URI;
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
import com.uade.tpo.grupo3.amancay.entity.dto.common.ErrorResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderRequest;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("orders")
public class OrdersController {
    
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getOrders(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            if (page == null || size == null)
                return ResponseEntity.ok(orderService.getOrders(PageRequest.of(0, Integer.MAX_VALUE)));
            return ResponseEntity.ok(orderService.getOrders(PageRequest.of(page, size)));
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        try {
            Optional<Order> result = orderService.getOrderById(orderId);
            if(result.isPresent())
                return ResponseEntity.ok(result.get());
            
            String errorMessage = "Request couldn't be completed because: That OrderID couldn't be found " + orderId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("NOT_FOUND", errorMessage));
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) {
        try {
            Order result = orderService.createOrder(request);
            return ResponseEntity.created(URI.create("/orders/" + result.getId())).body(result);
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("BAD_REQUEST", errorMessage));
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest request) {
        try {
            GenericResponse result = orderService.updateOrder(orderId, request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            GenericResponse result = orderService.deleteOrder(orderId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getOrdersByStatus(
            @PathVariable String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            if (page == null || size == null)
                return ResponseEntity.ok(orderService.getOrdersByStatus(status, PageRequest.of(0, Integer.MAX_VALUE)));
            return ResponseEntity.ok(orderService.getOrdersByStatus(status, PageRequest.of(page, size)));
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            if (page == null || size == null)
                return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId, PageRequest.of(0, Integer.MAX_VALUE)));
            return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId, PageRequest.of(page, size)));
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getOrdersByProductId(
            @PathVariable Long productId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        try {
            if (page == null || size == null)
                return ResponseEntity.ok(orderService.getOrdersByProductId(productId, PageRequest.of(0, Integer.MAX_VALUE)));
            return ResponseEntity.ok(orderService.getOrdersByProductId(productId, PageRequest.of(page, size)));
        } catch (Exception e) {
            String errorMessage = "Request couldn't be completed because: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("ERROR", errorMessage));
        }
    }
}
