package py.ecommerce.payment.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import py.ecommerce.payment.dto.OrderDto;

@FeignClient(name = "ordersrvc")
public interface OrderFeignClient {

    @PutMapping("update-status-order")
    public OrderDto updateStatusOrder(@RequestBody OrderDto orderDto);
}
