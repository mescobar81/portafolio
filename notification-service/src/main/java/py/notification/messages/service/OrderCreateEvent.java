package py.notification.messages.service;

import java.io.Serializable;

public record OrderCreateEvent(Long orderId, String customerEmail, Double totalAmount) implements Serializable {
}
