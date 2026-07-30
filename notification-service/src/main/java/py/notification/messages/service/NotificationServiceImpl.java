package py.notification.messages.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    private final Logger logger = LogManager.getLogger(NotificationServiceImpl.class);
    
    @Override
    @RabbitListener(queues = "notification.order.created.queue")
    public void handleOrderCreated(OrderCreateEvent orderCreateEvent) {
        logger.info("Mensaje recibido de RabbitMQ para la orden #{}", orderCreateEvent.orderId());
        
        // Simulación del envío de correo
        sendEmail(orderCreateEvent.customerEmail(), orderCreateEvent.orderId(), orderCreateEvent.totalAmount());
    }

    private void sendEmail(String toEmail, Long orderId, Double amount) {
        logger.info("Simulando envío de correo a: {}", toEmail);
        logger.info("Asunto: ¡Orden #{} confirmada!", orderId);
        logger.info("Cuerpo: Gracias por tu compra. Total abonado: ${}", amount.doubleValue());
    }
}
