package com.example.rain.service.impl;

import com.example.rain.eror.ResourceNotFoundException;
import com.example.rain.model.OrderItem;
import com.example.rain.repository.OrderItemRepository;
import com.example.rain.service.IOrderItemService;
import com.example.rain.util.OrderItemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderItemService implements IOrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderItemUtil orderItemUtil;

    // ----------------------------------------------------------------------------------------------------------

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    // ----------------------------------------------------------------------------------------------------------

    @Override
    public OrderItem getOrderItemById(String id) throws ResourceNotFoundException {

        return getById(id);
    }

    // ----------------------------------------------------------------------------------------------------------

    @Override
    public void deleteOrderItemById(String id) throws ResourceNotFoundException {

        orderItemRepository.delete(
                getOrderItemById(id)
        );
    }

    // ----------------------------------------------------------------------------------------------------------

    @Override
    public OrderItem updateOrderItem(final OrderItem orderItem) throws Exception {

        validateDocumentTypeCode(orderItem); // watch out for duplicating codes

        OrderItem documentTypeToUpdate = getById(orderItem.id);
        documentTypeToUpdate.setName(orderItem.name);
        documentTypeToUpdate.setDescription(orderItem.description);
        documentTypeToUpdate.setVersion(orderItem.getVersion());
        documentTypeToUpdate.setCode(orderItem.getCode());

        return orderItemRepository.save(documentTypeToUpdate);
    }

    // ----------------------------------------------------------------------------------------------------------

    public OrderItem getById(String id) throws ResourceNotFoundException {

        return orderItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("Entry not found for this id :: %s", id)
                )
        );
    }

    // ----------------------------------------------------------------------------------------------------------

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) throws Exception {

        validateDocumentTypeCode(orderItem); // watch out for duplicating codes

        // @Id of model may not have been set................
        orderItemUtil.invalidateOrderItemId(
                orderItem
        );

        orderItem.setDateCreated(new Date());
        orderItem.setLastUpdated(new Date());

        return orderItemRepository.save(orderItem);
    }

    // ----------------------------------------------------------------------------------------------------------

    private void validateDocumentTypeCode(OrderItem orderItem) throws Exception {

        if (Objects.isNull(orderItem.getCode()) || orderItem.getCode().isEmpty()) {
            throw new Exception("Code required");
        }

        OrderItem type = orderItemRepository.findByCode(orderItem.getCode());

        Boolean invalidCode = Boolean.TRUE; // pessimistic default

        if (Objects.nonNull(type)) { // implies found order item with same code
            if (Objects.nonNull(orderItem.id) && orderItem.id.equals(type.id)) {
                invalidCode = !invalidCode;
            }
        }
        else invalidCode = !invalidCode;

        if (invalidCode) throw new Exception("Code already exists");
    }
}
