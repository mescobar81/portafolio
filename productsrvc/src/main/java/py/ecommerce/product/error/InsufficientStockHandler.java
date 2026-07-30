package py.ecommerce.product.error;

public class InsufficientStockHandler extends RuntimeException{
     private final String status;
    public InsufficientStockHandler(String status, String message){
        super(message);
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
