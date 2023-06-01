package tipolt.andre.spring.controllers.dtos;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    
    private List<String> listProductId;

    private String userId;

    private Integer quantity;
}