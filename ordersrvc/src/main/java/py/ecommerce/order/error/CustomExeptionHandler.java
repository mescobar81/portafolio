package py.ecommerce.order.error;

import java.util.Map;

public class CustomExeptionHandler extends RuntimeException{

    private final int status;
    private final Map<String, Object> errorBody;
    public CustomExeptionHandler(int status, Map<String, Object> errorBody) {
        super(errorBody.containsKey("message")? String.valueOf(errorBody.get("message")):"Error en servicio externo");
        this.status = status;
        this.errorBody = errorBody;
    }
    
    public int getStatus() {
        return status;
    }
    public Map<String, Object> getErrorBody() {
        return errorBody;
    }
    
}
