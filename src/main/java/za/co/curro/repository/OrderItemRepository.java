package za.co.curro.repository;

import za.co.curro.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {

    OrderItem findByCode(String code);
}
