package tipolt.andre.spring.controllers.dtos;


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


    @NotEmpty(message = "Fill all fields")
    private List<String> listProductId;

    @NotNull(message = "Fill all fields")
    private String userId;

    @NotNull(message = "Fill all fields")
    private Integer quantity;
}