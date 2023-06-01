package tipolt.andre.spring.controllers.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    
    private String productId;

    private String userId;

    private Integer quantity;
}
;