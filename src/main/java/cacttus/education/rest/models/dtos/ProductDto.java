package cacttus.education.rest.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private String metalType;
    private String gemstone;
    private int stockQuantity;
}
