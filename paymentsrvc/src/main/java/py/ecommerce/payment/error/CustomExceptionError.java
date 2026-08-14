package py.ecommerce.payment.error;

import java.util.Map;

public class CustomExceptionError extends RuntimeException{


    private int status;
    private Map<String, Object> errorBody;
    public CustomExceptionError(int status, Map<String, Object> errorBody) {
        super(errorBody.containsKey("message") ? String.valueOf(errorBody.get("message")): "Error en servicio externo.");
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
