package za.co.curro.util;

import za.co.curro.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class OrderItemUtil {

    public void invalidateOrderItemId(OrderItem orderItem) {

        if (Objects.isNull(orderItem.id) || orderItem.id.isEmpty()) {

            orderItem.id = UUID.randomUUID().toString().replaceAll("-","");
        }
    }
}
