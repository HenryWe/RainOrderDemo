package za.co.curro.service;

import za.co.curro.eror.ResourceNotFoundException;
import za.co.curro.model.OrderItem;

import java.util.List;

public interface IOrderItemService {

    List<OrderItem> getOrderItems();
    OrderItem getOrderItemById(String id) throws ResourceNotFoundException;
    void deleteOrderItemById(String id) throws ResourceNotFoundException;
    OrderItem updateOrderItem(OrderItem documentType) throws Exception;
    OrderItem saveOrderItem(OrderItem documentType) throws Exception;
}
