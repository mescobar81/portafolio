package py.ecommerce.order.enums;

public enum RabbitMQ {
    EXCHANGE("order.exchange"),
    ROUTING_KEY("order.created");

    private final String value;
    RabbitMQ(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
