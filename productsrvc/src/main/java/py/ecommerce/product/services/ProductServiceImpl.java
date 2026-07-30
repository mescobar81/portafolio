package py.ecommerce.product.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import py.ecommerce.product.dtos.StockResponseUpdateDto;
import py.ecommerce.product.dtos.InventoryResponseDto;
import py.ecommerce.product.dtos.ProductDto;
import py.ecommerce.product.dtos.StockRequest;
import py.ecommerce.product.entities.Product;
import py.ecommerce.product.error.InsufficientStockHandler;
import py.ecommerce.product.error.ResponseNotFoundException;
import py.ecommerce.product.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto getById(Long id) {
        return repository.findById(id).map(existingProduct -> {
            return toDto(existingProduct);
        }).orElseThrow(() -> new ResponseNotFoundException("Producto no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        if (productDto.getId() != null && productDto.getId() > 0) {
            return repository.findById(productDto.getId())
                    .map(existingProduct -> {
                        existingProduct.setCode(productDto.getCode());
                        existingProduct.setName(productDto.getName());
                        existingProduct.setPrice(productDto.getPrice());
                        existingProduct.setStock(productDto.getStock());
                        return toDto(existingProduct);

                    }).orElseThrow(() -> new ResponseNotFoundException(
                            "Producto no encontrado con ID: " + productDto.getId()));
        }
        return toDto(repository.save(
                new Product(productDto.getCode(), productDto.getName(), productDto.getPrice(), productDto.getStock())));
    }

    @Override
    public List<ProductDto> getAll() {
        return ((List<Product>) repository.findAll()).stream()
                .map(p -> new ProductDto(p.getId(), p.getCode(), p.getName(), p.getPrice(), p.getStock(),
                        p.getIsInStock()))
                .toList();
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<InventoryResponseDto> findBySkuCodeIn(List<String> code) {
        return repository.findBySkuCodeIn(code).stream().map(
                p -> new InventoryResponseDto(p.getId(), p.getName(), p.getStock(), p.getIsInStock())).toList();
    }

    @Override
    @Transactional // Es crucial para asegurar que si algo falla, no se actualice a medias
    public StockResponseUpdateDto deductStock(List<StockRequest> stockRequest) {
        // 1. Convertir la lista a un Mapa indexado por ID. ¡Esto cambia el juego!
        // Ahora buscar un DTO por ID toma microsegundos.
        Map<Long, StockRequest> dtoMap = stockRequest.stream().collect(Collectors.toMap(p -> p.getId(), dto -> dto));

        // 2. Traer todos los productos en UNA sola consulta SQL
        List<Product> productsFromDb = (List<Product>) repository.findAllById(dtoMap.keySet());
        // 3. Modificar el stock de forma lineal (Complejidad O(N))
        for (Product p : productsFromDb) {
            // Buscamos el DTO que coincide con el producto actual
            StockRequest matchingDto = dtoMap.get(p.getId());
            if (matchingDto == null) {
                throw new ResponseNotFoundException("Producto no encontrado en la petición para el ID: " + p.getId());
            }
            if (matchingDto.getStock() > p.getStock()) {
                throw new InsufficientStockHandler("failure",
                        "Stock insuficiente para el ID: " + matchingDto.getId());
            }

            p.deductStock(matchingDto.getStock());
        }

        if(productsFromDb.isEmpty()){
            throw new ResponseNotFoundException("Productos no encontrado en la lista enviada: " + productsFromDb.size());
        }
        // 4. Guardar todos los productos con el stock actualizado en lote
        List<Product> updatedStocksList = (List<Product>) repository.saveAll(productsFromDb);
        // 5. Retornar la respuesta
        return new StockResponseUpdateDto("success",
                "Stock actualizado exitosamente en lote.", updatedStocksList.size());

    }

    @Override
    @Transactional
    public StockResponseUpdateDto retoreStock(List<StockRequest> productDtos) {
        // 1. Convertir la lista a un Mapa indexado por ID. ¡Esto cambia el juego!
        // Ahora buscar un DTO por ID toma microsegundos.
        Map<Long, StockRequest> dtoMap = productDtos.stream()
                .collect(Collectors.toMap(StockRequest::getId, dto -> dto));
        // 2. Traer todos los productos en UNA sola consulta SQL
        List<Product> productsFromDb = (List<Product>) repository.findAllById(dtoMap.keySet());
        // 3. Modificar el stock de forma lineal (Complejidad O(N))
        for (Product p : productsFromDb) {
            // Buscamos el DTO que coincide con el producto actual
            StockRequest stockRequest = dtoMap.get(p.getId());
            if (stockRequest == null) {
                throw new ResponseNotFoundException("Producto no encontrado en la petición para el ID: " + p.getId());
            }
            p.restoreStock(stockRequest.getStock());
        }
        // 4. Guardar todos los productos con el stock actualizado en lote
        List<Product> updatedStocksList = (List<Product>) repository.saveAll(productsFromDb);
        // 5. Retornar la respuesta
        return new StockResponseUpdateDto("success", "Stock actualizado exitosamente en lote.",
                updatedStocksList.size());
    }

    private ProductDto toDto(Product product) {
        return new ProductDto(product.getId(), product.getCode(), product.getName(), product.getPrice(),
                product.getStock(), product.getIsInStock());
    }
}
