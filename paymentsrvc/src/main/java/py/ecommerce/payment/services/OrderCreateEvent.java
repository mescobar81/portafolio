package py.ecommerce.payment.services;

import java.io.Serializable;

public record OrderCreateEvent(Long orderId, String customerEmail, Double totalAmount) implements Serializable {
}