package tipolt.andre.spring.dtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotEmpty(message = "Fill Products Field")
    private List<Long> listProductId;

    @NotNull(message = "Fill User Field")
    private Long userId;

    @NotNull(message = "Fill Quantity Fields")
    private Integer quantity;
}