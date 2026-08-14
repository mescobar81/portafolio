package py.ecommerce.order.dto;

public class StockRequest {

    private Long productId;
    private Long stock;

    public StockRequest() {
    }
    
    public StockRequest(Long id, Long stock) {
        this.productId = id;
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long id) {
        this.productId = id;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

}
