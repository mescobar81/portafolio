package py.ecommerce.product.services;

import java.util.List;

public interface CrudService<T> {
    List<T> getAll();
    T getById(Long id);
    T save(T t);
    void delete(Long id);
}
