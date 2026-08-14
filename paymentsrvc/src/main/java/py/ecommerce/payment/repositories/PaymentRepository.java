package py.ecommerce.payment.repositories;

import org.springframework.data.repository.CrudRepository;

import py.ecommerce.payment.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{
}
