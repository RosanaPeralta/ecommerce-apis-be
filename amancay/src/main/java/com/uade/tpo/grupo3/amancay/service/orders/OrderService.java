package com.uade.tpo.grupo3.amancay.service.orders;

import java.security.InvalidParameterException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.uade.tpo.grupo3.amancay.entity.Order;
import com.uade.tpo.grupo3.amancay.entity.dto.common.GenericResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderRequest;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderResponse;
import com.uade.tpo.grupo3.amancay.entity.dto.orders.OrderStatusUpdateRequest;

public interface OrderService {
    public Page<Order> getOrders(PageRequest pageRequest);

    public Optional<Order> getOrderById(Long id);

    public Order createOrder(OrderRequest order);

    public GenericResponse deleteOrder(Long id);

    public GenericResponse updateOrder(Long id, OrderRequest order) throws InvalidParameterException;
    
    public Page<Order> getOrdersByStatus(String status, PageRequest pageRequest);
    
    public Page<Order> getOrdersByCustomerId(Long customerId, PageRequest pageRequest);
    
    public Page<Order> getOrdersByProductId(Long productId, PageRequest pageRequest);
    
    public GenericResponse updateOrderStatus(Long id, OrderStatusUpdateRequest statusUpdate) throws InvalidParameterException;
    
    public OrderResponse convertToOrderResponse(Order order);
}
