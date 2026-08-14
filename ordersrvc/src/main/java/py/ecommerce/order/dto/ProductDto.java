package py.ecommerce.order.dto;

public class ProductDto {
    private Long productId;

    public ProductDto() {
    }

    public ProductDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
