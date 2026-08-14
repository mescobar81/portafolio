package py.ecommerce.product.dtos;

public class StockRequest {

    private Long productId;
    private Long stock;
    public StockRequest() {}
    
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getStock() {
        return stock;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }
}
