package py.notification.messages.service;

public interface NotificationService {

    void handleOrderCreated(OrderCreateEvent orderCreateEvent);
}
