package py.ecommerce.order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private Long id;
    private LocalDate date;
    private List<OrderDetailDto> details;
    private BigDecimal amount;
    private String statusOrder;
    public OrderDto() {}

    
    public OrderDto(Long id, LocalDate date, BigDecimal amount, List<OrderDetailDto> details, String statusOrder) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.details = details;
        this.statusOrder = statusOrder;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public List<OrderDetailDto> getDetails() {
        return details;
    }
    public void setDetails(List<OrderDetailDto> orders) {
        this.details = orders;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setTotal(BigDecimal total) {
        this.amount = total;
    }


    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
