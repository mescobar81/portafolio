package py.ecommerce.order.enums;

public enum OrderStatus {

    APROBADA("APROBADA"),
    PENDING("PENDING"),
    CANCEL("CANCEL");

    private String value;

    OrderStatus(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
