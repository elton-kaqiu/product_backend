package cacttus.education.rest.mappers;
import cacttus.education.rest.models.dtos.ProductDto;
import cacttus.education.rest.models.entities.ProductEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class ProductMapper {
    public ProductEntity toEntity(ProductDto from) {
        ProductEntity to = new ProductEntity();
        BeanUtils.copyProperties(from, to);//what does this method??
        to.setRegisteredBy("admin");
        to.setRegisteredDate(LocalDate.now());
        return to;
    }

    public ProductDto toDto(ProductEntity from) {
        ProductDto to = new ProductDto();
        BeanUtils.copyProperties(from, to);
        return to;
    }

}
