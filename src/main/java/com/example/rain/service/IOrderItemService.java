package com.example.rain.service;

import com.example.rain.eror.ResourceNotFoundException;
import com.example.rain.model.OrderItem;

import java.util.List;

public interface IOrderItemService {

    List<OrderItem> getOrderItems();
    OrderItem getOrderItemById(String id) throws ResourceNotFoundException;
    void deleteOrderItemById(String id) throws ResourceNotFoundException;
    OrderItem updateOrderItem(OrderItem documentType) throws Exception;
    OrderItem saveOrderItem(OrderItem documentType) throws Exception;
}
