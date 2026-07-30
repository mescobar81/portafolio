package py.ecommerce.product.services;

import java.util.List;

import py.ecommerce.product.dtos.StockResponseUpdateDto;
import py.ecommerce.product.dtos.InventoryResponseDto;
import py.ecommerce.product.dtos.ProductDto;
import py.ecommerce.product.dtos.StockRequest;

public interface ProductService extends CrudService<ProductDto> {
    List<InventoryResponseDto> findBySkuCodeIn(List<String> code);
    StockResponseUpdateDto deductStock(List<StockRequest> productDtos);
    StockResponseUpdateDto retoreStock(List<StockRequest> productDtos);
}
