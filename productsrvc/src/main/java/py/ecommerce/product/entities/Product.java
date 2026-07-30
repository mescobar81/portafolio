package py.ecommerce.product.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 12, nullable = false)
    private String code;
    @Column(length = 60, nullable = false)
    private String name;
    @Column(length = 12, nullable = false)
    private Double price;
    @Column(length = 4, nullable = false)
    private Long stock;
    @Column(name = "in_stock")
    private Boolean isInStock;
    @Column(name = "user_ins")
    private String userIns;
    @Column(name = "date_ins")
    private LocalDate dateIns;

    public Product() {
    }

    public Product(String code, String name, Double price, Long stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @PrePersist
    public void updateStockStatus(){
        isInStock = stock > 0;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
        this.isInStock = stock > 0;
    }

    public void deductStock(Long quantity){
        this.stock-=quantity;
    }
    public void restoreStock(Long quantity){
        this.stock+=quantity;
    }
    public Boolean getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(Boolean isInStock) {
        this.isInStock = isInStock;
    }

    public String getUserIns() {
        return userIns;
    }

    public void setUserIns(String userIns) {
        this.userIns = userIns;
    }

    public LocalDate getDateIns() {
        return dateIns;
    }

    public void setDateIns(LocalDate dateIns) {
        this.dateIns = dateIns;
    }
}
