package py.ecommerce.payment.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.ecommerce.payment.dto.OrderDto;
import py.ecommerce.payment.dto.PaymentDto;
import py.ecommerce.payment.entities.Payment;
import py.ecommerce.payment.enums.PaymentMethod;
import py.ecommerce.payment.enums.PaymentStatus;
import py.ecommerce.payment.error.CustomExceptionError;
import py.ecommerce.payment.http.OrderFeignClient;
import py.ecommerce.payment.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final OrderFeignClient orderFeignClient;
    private OrderDto orderDto;

    public PaymentServiceImpl(PaymentRepository repository, OrderFeignClient orderFeignClient) {
        this.repository = repository;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    @RabbitListener(queues = "payment.order.created.queue")
    public void handlerOrderPayment(OrderCreateEvent createEvent) {
        orderDto = getReferenceOrder(createEvent.orderId());
        System.out.println("CreateEvent Order ID: " + orderDto.getId());

    }

    @Override
    @Transactional
    public PaymentDto save(PaymentDto paymentDto) {
        if(orderDto == null){
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("code", "99");
            errorBody.put("message", "Ningún pedido creado para procesar pago. Verifique");
            throw new CustomExceptionError(400, errorBody);
        }
        Payment savePayment = repository.save(new Payment(orderDto.getId(), paymentDto.getAmount(),
                PaymentStatus.SUCCESS, PaymentMethod.valueOf(paymentDto.getMethod())));
        orderDto.setAmount(savePayment.getAmount());
        orderDto.setStatusOrder("APROBADA");
        orderFeignClient.updateStatusOrder(orderDto);
        return toDto(savePayment);
    }

    /**
     * Utilitario para convertir entity a dto
     * 
     * @param payment
     * @return
     */
    private PaymentDto toDto(Payment payment) {
        return new PaymentDto(payment.getId(), orderDto, payment.getTransactionId(), payment.getAmount(),
                payment.getStatus().name(), payment.getMethod().name());
    }

    /**
     * devuelve una referencia a OrderDto con el id seteado
     * @param id
     * @return
     */
    private OrderDto getReferenceOrder(Long id){
        return new OrderDto(id);
    }
}
