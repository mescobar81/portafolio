package py.ecommerce.order.services;

import java.util.List;
import py.ecommerce.order.dto.InventoryResponseDto;
import py.ecommerce.order.dto.OrderDto;

public interface OrderService extends CrudService<OrderDto>{
    
    List<InventoryResponseDto> findBySkuCodeIn(List<String> code);
    OrderDto updateStatusOrderById(Long id);
}
