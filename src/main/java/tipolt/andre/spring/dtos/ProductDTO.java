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
    
    @NotNull(message = "Fill Name Field")
    private String name;

    @NotNull(message = "Fill Price Field")
    private Double price;

    @NotNull(message = "Fill Category Field")
    private String categoryId;


}
