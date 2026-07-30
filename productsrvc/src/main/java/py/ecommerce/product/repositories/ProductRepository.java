package py.ecommerce.product.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import py.ecommerce.product.entities.Product;

public interface ProductRepository extends CrdRepository<Product> {

    @Query("SELECT p FROM Product p WHERE p.code in (:code)")
    List<Product> findBySkuCodeIn(List<String> code);
}
