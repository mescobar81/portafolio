package py.ecommerce.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentsrvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsrvcApplication.class, args);
	}

}
