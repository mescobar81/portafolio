package py.ecommerce.product.dtos;

import java.util.HashMap;
import java.util.Map;

public class ResponseMessage {

    private Map<String, String> messages;

    public ResponseMessage(){
        messages = new HashMap<>();
    }
    public void addMessage(String code, String message){
        messages.put(code, message);
    }
    public Map<String, String> getMessages(){
        return messages;
    }
}
