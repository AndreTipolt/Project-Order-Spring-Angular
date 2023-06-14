package tipolt.andre.spring.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    
    @NotNull(message = "Fill All Fields")
    private String name;

    @NotNull(message = "Fill All Fields")
    private Double price;

    @NotNull(message = "Fill All Fields")
    private String categoryId;


}
