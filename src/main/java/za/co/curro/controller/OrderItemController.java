package za.co.curro.controller;

import za.co.curro.eror.ResourceNotFoundException;
import za.co.curro.model.OrderItem;
import za.co.curro.service.IOrderItemService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value="Jwt POC Demo", description="Operations pertaining to Jwt POC Demo")
@RequestMapping(value="/order/item")
@RestController
public class OrderItemController {

    IOrderItemService orderItemService;

    public OrderItemController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // ----------------------------------------------------------------------------------------------------------

    @GetMapping("/list")
    @ApiOperation(
            value = "List all Order Items",
            notes = "Return List<OrderItem> if retrieved successfully",
            response = ResponseEntity.class,
            authorizations = { @Authorization(value="jwtToken") }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully acquired list of order items", response = List.class)
    })
    public List<OrderItem> listAllOrderItems() {

        log.info("list All Order Items");
        return orderItemService.getOrderItems();
    }

    // ----------------------------------------------------------------------------------------------------------

    @GetMapping("{id}")
    @ApiOperation(
            value = "Get Order Item by ID",
            notes = "Return ResponseEntity<OrderItem> if retrieved successfully",
            response = ResponseEntity.class,
            authorizations = { @Authorization(value="jwtToken") }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned Order Item by ID", response = ResponseEntity.class)
    })
    @Validated
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") String id) throws ResourceNotFoundException {

        log.info("get Order Item By Id"); // preliminary log entry to show ID tracing
        return ResponseEntity.ok().body(
                orderItemService.getOrderItemById(id)
        );
    }

    // ----------------------------------------------------------------------------------------------------------

    @DeleteMapping("{id}")
    @ApiOperation(
            value = "Delete Order Item from DB",
            notes = "Return message indicating successful deletion",
            response = Map.class,
            authorizations = { @Authorization(value="jwtToken") }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted OrderItem from DB", response = Map.class)
    })
    @Validated
    public Map<String, Boolean> deleteOrderItemById (@PathVariable("id") String id) throws ResourceNotFoundException {

        log.info("Delete Order Item By Id"); // preliminary log entry to show ID tracing

        orderItemService.deleteOrderItemById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // ----------------------------------------------------------------------------------------------------------

    @PutMapping()
    @ApiOperation(
            value = "Update Order Item in DB",
            notes = "Return Order Item if updated successfully",
            response = ResponseEntity.class,
            authorizations = { @Authorization(value="jwtToken") }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated Order Item", response = ResponseEntity.class)
    })
    @Validated
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem orderItem) throws Exception {

        log.info("Update Order Item");
        return ResponseEntity.ok(
                orderItemService.updateOrderItem(orderItem)
        );
    }

    // ----------------------------------------------------------------------------------------------------------

    @PostMapping()
    @ApiOperation(
            value = "Save Order Item to DB",
            notes = "Return Order Item if persisted successfully",
            response = OrderItem.class,
            authorizations = { @Authorization(value="jwtToken") }
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully persisted Order Item", response = OrderItem.class)
    })
    @Validated
    public OrderItem saveOrderItem(@RequestBody OrderItem orderItem) throws Exception {

        log.info("Save Order Item");
        return orderItemService.saveOrderItem(
                orderItem
        );
    }

    // ----------------------------------------------------------------------------------------------------------

}
