package tipolt.andre.spring.controllers.factories;

import java.util.List;

import tipolt.andre.spring.dtos.OrderDTO;

public class OrderFactory {
    

    public static OrderDTO createOrderDTO(){

        OrderDTO orderDTO = new OrderDTO(List.of("1"), "1", 1);

        return orderDTO;
    }

    public static OrderDTO createOrderDTOWithInvalidUserId() {
        String nonExistingUserId = "0";

        OrderDTO orderDTO = new OrderDTO(List.of("1"), nonExistingUserId, 1);
        return orderDTO;
    }

    public static OrderDTO createOrderDTOWithInvalidProductID(){
        String nonExistingProductID = "0";

        OrderDTO orderDTO = new OrderDTO(List.of(nonExistingProductID), "1", 1);
        return orderDTO;
    }

    public static OrderDTO createOrderDTOWithoutUserIdField(){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setQuantity(1);
        orderDTO.setListProductId(List.of("1"));

        return orderDTO;
    }
}
