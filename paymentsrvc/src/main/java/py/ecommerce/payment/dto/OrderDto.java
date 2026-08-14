package py.ecommerce.payment.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDto implements Serializable{

    private Long id;
    private BigDecimal amount;
    private String statusOrder;

    public OrderDto() {}
    public OrderDto(Long id) {
        this.id = id;
    }
    public OrderDto(Long id, BigDecimal amount, String statusOrder) {
        this.id = id;
        this.amount = amount;
        this.statusOrder = statusOrder;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getStatusOrder() {
        return statusOrder;
    }
    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
