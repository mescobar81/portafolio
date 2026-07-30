package py.ecommerce.product.dtos;

public class StockResponseUpdateDto {

    private String status;
    private String message;
    private Integer processedCount;

    public StockResponseUpdateDto(){}

    public StockResponseUpdateDto(String status, String message, Integer processedCount){
        this.status = status;
        this.message = message;
        this.processedCount = processedCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String skuCode) {
        this.status = skuCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getProcessedCount() {
        return processedCount;
    }

    public void setProcessedCount(Integer processedCount) {
        this.processedCount = processedCount;
    }
}
