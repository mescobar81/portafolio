package py.ecommerce.order.dto;

public class StockRequest {

    private Long id;
    private Long stock;

    public StockRequest() {
    }
    
    public StockRequest(Long id, Long stock) {
        this.id = id;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

}
