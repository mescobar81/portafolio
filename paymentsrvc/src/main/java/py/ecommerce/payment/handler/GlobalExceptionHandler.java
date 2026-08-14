package py.ecommerce.payment.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import py.ecommerce.payment.error.CustomExceptionError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptionError.class)
    public ResponseEntity<?> handlerCustomExceptionError(CustomExceptionError ex){
        return ResponseEntity.status(ex.getStatus()).body(ex.getErrorBody());
    }
}
