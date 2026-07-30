package py.ecommerce.order.services;

public interface CrudService<T> {
    T save(T t);
}
