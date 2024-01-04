package tipolt.andre.spring.dtos;

import javax.validation.constraints.NotNull;
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
    private Double fowardPrice;

    @NotNull(message = "Fill Category Field")
    private Long categoryId;

    @NotNull(message = "Fill Installments Field")
    private Integer installments;

    @NotNull(message = "Fill SpotPrice Field")
    private Double spotPrice;

    @NotNull(message = "Fill PixDiscounts Field")
    private Integer pixDiscount;

    @NotNull(message = "Fill Description Field")
    private String description;
}
