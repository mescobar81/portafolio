package py.ecommerce.payment.services;

import py.ecommerce.payment.dto.PaymentDto;

public interface PaymentService {

    PaymentDto save(PaymentDto payment);
    void handlerOrderPayment(OrderCreateEvent createEvent);
}
