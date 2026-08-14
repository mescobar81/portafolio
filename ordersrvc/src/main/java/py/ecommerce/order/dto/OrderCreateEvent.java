package py.ecommerce.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public record OrderCreateEvent(Long orderId, String customerEmail, BigDecimal totalAmount) implements Serializable {
}
