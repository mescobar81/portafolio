package py.ecommerce.order.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.ecommerce.order.dto.OrderDto;
import py.ecommerce.order.services.OrderService;

@RestController
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("save")
    public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<OrderDto>(service.save(orderDto), HttpStatus.OK);
    }

    @GetMapping("inventory")
    public ResponseEntity<?> findBySkuCodeIn(@RequestParam List<String> codes) {
        return new ResponseEntity<>(service.findBySkuCodeIn(codes), HttpStatus.OK);
    }

    @PutMapping("update-status-order")
    public ResponseEntity<?> updateStatusOrderById(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(service.updateStatusOrder(orderDto), HttpStatus.OK);
    }
}
