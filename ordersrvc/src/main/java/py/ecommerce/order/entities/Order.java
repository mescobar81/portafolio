package py.ecommerce.order.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import py.ecommerce.order.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order", orphanRemoval = true)
    private List<OrderDetail> details;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_order")
    private OrderStatus statusOrder;

    public Order() {
    }

    public Order(LocalDate date, BigDecimal amount, OrderStatus statusOrder) {
        this.date = date;
        this.amount = amount;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal total) {
        this.amount = total;
    }

    public void addOrder(OrderDetail orderDetail) {
        if (details == null) {
            details = new ArrayList<>();
        }
        details.add(orderDetail);
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> orders) {
        this.details = orders;
    }

    public OrderStatus getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(OrderStatus statusOrder) {
        this.statusOrder = statusOrder;
    }
}
