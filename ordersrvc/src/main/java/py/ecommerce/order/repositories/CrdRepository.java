package py.ecommerce.order.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrdRepository<T> extends CrudRepository<T, Long> {

}
