package py.ecommerce.product.dtos;

public class InventoryResponseDto {

    private Long id;
    private String name;
    private Long stock;
    private Boolean isInStock;

    public InventoryResponseDto() {}

    
    public InventoryResponseDto(Long id, String name, Long stock, Boolean isInStock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.isInStock = isInStock;
    }

    public Long getId() {
        return id;
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
    public Long getStock() {
        return stock;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }
    public Boolean getIsInStock() {
        return isInStock;
    }
    public void setIsInStock(Boolean isInStock) {
        this.isInStock = isInStock;
    }
}
