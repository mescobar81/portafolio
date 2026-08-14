package py.ecommerce.order.listener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import py.ecommerce.order.dto.OrderCreateEvent;
import py.ecommerce.order.enums.RabbitMQ;

@Component
public class OrderEventListener {

    private final RabbitTemplate rabbitTemplate;

    public OrderEventListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreatedCommit(OrderCreateEvent createEvent){
        // Este código se ejecuta SOLAMENTE después de que el COMMIT en BD fue exitoso
        rabbitTemplate.convertAndSend(
            RabbitMQ.EXCHANGE.getValue(),
            RabbitMQ.ROUTING_KEY.getValue(),
            createEvent);
    }
}
