package cacttus.education.rest.services;

import cacttus.education.rest.models.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity add(ProductEntity product);

    List<ProductEntity> getAll();

    ProductEntity getById(long id);

    void deleteById(long id);

    ProductEntity modify(long id, ProductEntity product);

}
