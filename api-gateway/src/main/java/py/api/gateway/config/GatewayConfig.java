package py.api.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

@Component
public class GatewayConfig {

 @Autowired
    private org.springframework.cloud.gateway.config.GatewayProperties properties;

    @PostConstruct
    public void printRoutes() {
        System.out.println("Rutas cargadas: " + properties.getRoutes().size());
        properties.getRoutes().forEach(System.out::println);
    }
}
