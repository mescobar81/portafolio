package py.ecommerce.order.dto;

public class OrderDetailDto {

    private Long id;
    private ProductDto productDto;
    private Long quantity;

    public OrderDetailDto() {}

    public OrderDetailDto(Long id, ProductDto productDto, Long quantity) {
        this.id = id;
        this.productDto = productDto;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ProductDto getProductDto() {
        return productDto;
    }
    public void setProductDto(ProductDto product) {
        this.productDto = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
