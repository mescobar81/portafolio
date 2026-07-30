package py.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrdersrvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersrvcApplication.class, args);
	}

}
