package py.ecommerce.product.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import py.ecommerce.product.dtos.ResponseMessage;
import py.ecommerce.product.error.InsufficientStockHandler;
import py.ecommerce.product.error.ResponseNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseNotFoundException.class)
    public ResponseEntity<?> handlerExceptionResponse(ResponseNotFoundException ex) {
        ResponseMessage message = new ResponseMessage();
        message.addMessage("code", "99");
        message.addMessage("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessages());
    }

    @ExceptionHandler(InsufficientStockHandler.class)
    public ResponseEntity<Map<String, String>> handlerExceptionInsufficientStock(InsufficientStockHandler ex) {
        ResponseMessage message = new ResponseMessage();
        message.addMessage("status", ex.getStatus());
        message.addMessage("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.getMessages());
    }
}
