package py.ecommerce.order.http;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import py.ecommerce.order.decoder.GenericFeignErrorDecoder;
import py.ecommerce.order.dto.InventoryResponseDto;
import py.ecommerce.order.dto.StockRequest;
import py.ecommerce.order.dto.StockResponseUpdateDto;

@FeignClient(name = "productsrvc", configuration = GenericFeignErrorDecoder.class)
public interface ProductFeingClient {

    @GetMapping("inventory")
    List<InventoryResponseDto> findBySkuCodeIn(@RequestParam List<String> codes);

    @PutMapping("deduct-stock")
    StockResponseUpdateDto deductStock(@RequestBody List<StockRequest> stockRequest);

    
    @PutMapping("restore-stock")
    StockResponseUpdateDto restoreStock(@RequestBody List<StockRequest> stockRequest);
}
