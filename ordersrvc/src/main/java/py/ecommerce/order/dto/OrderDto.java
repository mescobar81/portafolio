package py.ecommerce.order.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private Long id;
    private LocalDate date;
    private List<OrderDetailDto> orders;
    private Double total;
    private String statusOrder;
    public OrderDto() {}

    
    public OrderDto(Long id, LocalDate date, Double total, List<OrderDetailDto> orders, String statusOrder) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.orders = orders;
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
    public List<OrderDetailDto> getOrders() {
        return orders;
    }
    public void setOrders(List<OrderDetailDto> orders) {
        this.orders = orders;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }


    public String getStatusOrder() {
        return statusOrder;
    }


    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
