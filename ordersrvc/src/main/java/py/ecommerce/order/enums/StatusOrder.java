package py.ecommerce.order.enums;

public enum StatusOrder {

    APROBADA("APROBADA"),
    PENDING("PENDING"),
    CANCEL("CANCEL");

    private String value;

    StatusOrder(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
