package py.ecommerce.order.services;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.ecommerce.order.dto.InventoryResponseDto;
import py.ecommerce.order.dto.OrderCreateEvent;
import py.ecommerce.order.dto.OrderDetailDto;
import py.ecommerce.order.dto.OrderDto;
import py.ecommerce.order.dto.ProductDto;
import py.ecommerce.order.dto.StockRequest;
import py.ecommerce.order.entities.Order;
import py.ecommerce.order.entities.OrderDetail;
import py.ecommerce.order.enums.OrderStatus;
import py.ecommerce.order.error.CustomExeptionHandler;
import py.ecommerce.order.http.ProductFeingClient;
import py.ecommerce.order.repositories.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ProductFeingClient feingClient;
    private final ApplicationEventPublisher eventPublisher;

    public OrderServiceImpl(OrderRepository repository,
            ProductFeingClient feingClient,
            ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.feingClient = feingClient;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public OrderDto save(OrderDto order) {
        List<StockRequest> stockRequests = order.getDetails().stream()
                .map(o -> new StockRequest(o.getProductDto().getProductId(), o.getQuantity())).toList();

        Order newOrder = new Order(order.getDate(), order.getAmount(), OrderStatus.PENDING);

        for (StockRequest sr : stockRequests) {
            newOrder.addOrder(new OrderDetail(sr.getProductId(), newOrder, sr.getStock()));
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

        // 2. En lugar de enviar a RabbitMQ directamente, disparamos el evento interno
        // de Spring
        publishEvent(new OrderCreateEvent(orderDto.getId(), "angel.me81@gmail.com",
                orderDto.getAmount()));
        return orderDto;
    }

    @Override
    public List<InventoryResponseDto> findBySkuCodeIn(List<String> codes) {
        return feingClient.findBySkuCodeIn(codes);
    }

    @Override
    @Transactional
    public OrderDto updateStatusOrder(OrderDto orderDto) {
        return repository.findById(orderDto.getId().longValue()).stream()
                .map(o -> {
                    OrderStatus statusOrder = OrderStatus.valueOf(orderDto.getStatusOrder());
                    o.setStatusOrder(statusOrder);
                    return toDto(repository.save(o));
                }).findFirst()
                .orElseThrow(() -> new CustomExeptionHandler(99,
                        Map.of("message", "Error al actualizar la orden con ID: " + orderDto.getId().longValue())));
    }

    /*
     * En lugar de enviar a RabbitMQ directamente, disparamos el evento interno de
     * Spring
     */
    private void publishEvent(OrderCreateEvent createEvent) {
        eventPublisher.publishEvent(createEvent);
    }

    private OrderDto toDto(Order order) {
        List<OrderDetailDto> detailDtos = order.getDetails().stream().map(
                o -> {
                    ProductDto productDto = new ProductDto(o.getProductId());
                    return new OrderDetailDto(o.getId(), productDto, o.getQuantity());
                }).toList();
        return new OrderDto(order.getId(), order.getDate(), order.getAmount(), detailDtos,
                order.getStatusOrder().getValue());
    }

}
