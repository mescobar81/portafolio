package py.ecommerce.order.decoder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import feign.Response;
import feign.codec.ErrorDecoder;
import py.ecommerce.order.error.CustomFeignException;
import tools.jackson.databind.ObjectMapper;

public class GenericFeignErrorDecoder implements ErrorDecoder {
    // private final ErrorDecoder defaultDecoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            // Leemos el cuerpo del error como un mapa genérico de objetos
            Map<String, Object> errorBody = mapper.readValue(bodyIs,
                    mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class));
            // Retornamos nuestra excepción genérica con los datos reales del error
            return new CustomFeignException(response.status(), errorBody);
        } catch (Exception e) {
            // Si el cuerpo no era un JSON o falló la lectura, usamos un fallback controlado
            Map<String, Object> fallbackBody = new HashMap<>();
            fallbackBody.put("status", "failure");
            fallbackBody.put("message", "Error de comunicación con el servicio externo");
            return new CustomFeignException(response.status(), fallbackBody);
        }
    }

}
