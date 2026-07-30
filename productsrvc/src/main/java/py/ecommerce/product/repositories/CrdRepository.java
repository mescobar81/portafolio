package py.ecommerce.product.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean // <-- ESTO EVITA EL ERROR DE CREACIÓN DE BEAN
public interface CrdRepository<T> extends CrudRepository<T, Long>{
    
}
