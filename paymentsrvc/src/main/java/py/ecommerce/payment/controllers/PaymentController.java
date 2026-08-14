package py.ecommerce.payment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import py.ecommerce.payment.dto.PaymentDto;
import py.ecommerce.payment.services.PaymentService;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody PaymentDto paymentDto){
        return new ResponseEntity<>(paymentService.save(paymentDto), HttpStatus.OK);
    }
}
