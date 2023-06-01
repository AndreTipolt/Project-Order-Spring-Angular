package tipolt.andre.spring.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tipolt.andre.spring.models.CategoryModel;
import tipolt.andre.spring.models.ProductModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryProductDTO {
    
    private ProductModel product;

    private CategoryModel category;
}
