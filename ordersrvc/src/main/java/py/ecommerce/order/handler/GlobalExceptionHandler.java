package py.ecommerce.order.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import py.ecommerce.order.error.CustomExeptionHandler;
import py.ecommerce.order.error.CustomFeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<?> handlerStockInsuficient(CustomFeignException ex) {
        // Retornamos exactamente el mismo JSON de error y el mismo estatus HTTP que envió el microservicio
         return ResponseEntity.status(ex.getStatus()).body(ex.getErrorBody());
        
    }

    @ExceptionHandler(CustomExeptionHandler.class)
    public ResponseEntity<?> handlerCustomExecption(CustomExeptionHandler ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getErrorBody());
    }
}
