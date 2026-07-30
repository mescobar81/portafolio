package py.ecommerce.product.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.ecommerce.product.dtos.ProductDto;
import py.ecommerce.product.dtos.StockRequest;
import py.ecommerce.product.services.ProductService;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<ProductDto>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<List<ProductDto>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        return new ResponseEntity<ProductDto>(service.save(product), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto product) {
        return new ResponseEntity<ProductDto>(service.save(product), HttpStatus.OK);
    }

    @GetMapping("inventory")
    public ResponseEntity<?> verifyStock(@RequestParam List<String> codes) {
        return new ResponseEntity<>(service.findBySkuCodeIn(codes), HttpStatus.OK);
    }

    @PutMapping("deduct-stock")
    public ResponseEntity<?> deductStock(@RequestBody List<StockRequest> stockRequest) {
        return new ResponseEntity<>(service.deductStock(stockRequest), HttpStatus.OK);
    }

    @PutMapping("restore-stock")
    public ResponseEntity<?> restoreStock(@RequestBody List<StockRequest> stockRequest) {
        return new ResponseEntity<>(service.retoreStock(stockRequest), HttpStatus.OK);
    }
}
