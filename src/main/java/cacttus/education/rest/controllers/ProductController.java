package cacttus.education.rest.controllers;

import cacttus.education.rest.mappers.ProductMapper;
import cacttus.education.rest.models.dtos.ProductDto;
import cacttus.education.rest.models.entities.ProductEntity;
import cacttus.education.rest.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/products")
@CrossOrigin
public class ProductController {
    private final ProductMapper mapper;
    private final ProductService service;

    public ProductController(ProductMapper mapper, ProductService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(
                service.getAll()
                        .stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDto> registerProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(mapper.toDto(service.add(mapper.toEntity(productDto))));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable Long id) {
        try {
            ProductEntity product = service.getById(id);
            if (product != null) {
                service.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable Long id, @RequestBody ProductDto updatedProductDto) {
        return Optional.ofNullable(service.modify(id, mapper.toEntity(updatedProductDto)))
                .map(updatedProduct -> new ResponseEntity<>(mapper.toDto(updatedProduct), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /*    @PutMapping("/{id}")
        public ResponseEntity<ProductDto> updateProductById(@PathVariable Long id, @RequestBody ProductDto updatedProductDto) {
            ProductEntity updatedProduct = service.modify(id, mapper.toEntity(updatedProductDto));
            if (updatedProduct != null) {
                return new ResponseEntity<>(mapper.toDto(updatedProduct), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }*/
}
