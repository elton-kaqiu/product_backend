package cacttus.education.rest.services.impl;

import cacttus.education.rest.models.entities.ProductEntity;
import cacttus.education.rest.repositories.ProductRepository;
import cacttus.education.rest.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public ProductEntity add(ProductEntity product) {
        return repository.save(product);
    }

    @Override
    public List<ProductEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public ProductEntity getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductEntity modify(long id, ProductEntity updatedProduct) {
        return repository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setMetalType(updatedProduct.getMetalType());
                    existingProduct.setGemstone(updatedProduct.getGemstone());
                    existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
                    existingProduct.setRegisteredBy(updatedProduct.getRegisteredBy());
                    existingProduct.setRegisteredDate(updatedProduct.getRegisteredDate());
                    return repository.save(existingProduct);
                })
                .orElse(null);
    }

}
