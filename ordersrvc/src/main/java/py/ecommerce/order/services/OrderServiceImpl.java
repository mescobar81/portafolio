package py.ecommerce.order.services;

import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.ecommerce.order.dto.InventoryResponseDto;
import py.ecommerce.order.dto.OrderCreateEvent;
import py.ecommerce.order.dto.OrderDetailDto;
import py.ecommerce.order.dto.OrderDto;
import py.ecommerce.order.dto.StockRequest;
import py.ecommerce.order.entities.Order;
import py.ecommerce.order.entities.OrderDetail;
import py.ecommerce.order.enums.RabbitMQ;
import py.ecommerce.order.enums.StatusOrder;
import py.ecommerce.order.http.ProductFeingClient;
import py.ecommerce.order.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductFeingClient feingClient;
    private final RabbitTemplate rabbitTemplate;

    public OrderServiceImpl(OrderRepository repository,
            ProductFeingClient feingClient,
            RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.feingClient = feingClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public OrderDto save(OrderDto order) {
        List<StockRequest> stockRequests = order.getOrders().stream()
                .map(o -> new StockRequest(o.getProductId(), o.getQuantity())).toList();

        Order newOrder = new Order(order.getDate(), order.getTotal(), StatusOrder.PENDING.getValue());

        for (StockRequest sr : stockRequests) {
            newOrder.addOrder(new OrderDetail(sr.getId(), sr.getStock()));
        }
        feingClient.deductStock(stockRequests);
        OrderDto orderDto = null;
        try {
            // 1. Lógica para guardar la orden en la base de datos
            orderDto = toDto(repository.save(newOrder));
        } catch (Exception e) {
            feingClient.restoreStock(stockRequests);
            // return updateStatusOrderById(order.getId());
            throw new RuntimeException("Error al guardar la orden, stock restaurado.", e);
        }

        // 2. Publicar el evento en RabbitMQ
        convertAndSend(RabbitMQ.EXCHANGE.getValue(), RabbitMQ.ROUTING_KEY.getValue(),
                new OrderCreateEvent(orderDto.getId(), "angel.me81@gmail.com",
                        orderDto.getTotal()));
        return orderDto;
    }

    @Override
    public List<InventoryResponseDto> findBySkuCodeIn(List<String> codes) {
        return feingClient.findBySkuCodeIn(codes);
    }

    @Override
    @Transactional
    public OrderDto updateStatusOrderById(Long id) {
        return repository.findById(id).stream()
                .map(o -> {
                    o.setStatusOrder(StatusOrder.CANCEL.getValue());
                    return toDto(repository.save(o));
                }).findFirst().orElseThrow(() -> new RuntimeException("Error al actualizar la orden."));
    }

    //Publicar el evento en RabbitMQ
    private void convertAndSend(String exchange, String routing, OrderCreateEvent createEvent) {
        rabbitTemplate.convertAndSend(
                exchange,
                routing,
                createEvent);
    }

    private OrderDto toDto(Order order) {
        List<OrderDetailDto> detailDtos = order.getOrders().stream().map(
                o -> new OrderDetailDto(o.getId(), o.getProductId(), o.getQuantity())).toList();
        return new OrderDto(order.getId(), order.getDate(), order.getTotal(), detailDtos, order.getStatusOrder());
    }

}
