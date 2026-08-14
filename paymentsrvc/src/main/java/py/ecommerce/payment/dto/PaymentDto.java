package py.ecommerce.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentDto implements Serializable{

    private Long id;
    private OrderDto orderDto;
    private String transactionId;
    private BigDecimal amount;
    private String status;
    private String method;
    
    public PaymentDto() {}

    public PaymentDto(Long id, OrderDto orderDto, String transactionId, BigDecimal amount, String status,
            String method) {
        this.id = id;
        this.orderDto = orderDto;
        this.transactionId = transactionId;
        this.amount = amount;
        this.status = status;
        this.method = method;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public OrderDto getOrderDto() {
        return orderDto;
    }
    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
}
