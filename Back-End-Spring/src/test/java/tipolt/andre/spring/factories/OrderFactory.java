// package tipolt.andre.spring.factories;

// import java.util.List;

// import tipolt.andre.spring.dtos.OrderDTO;

// public class OrderFactory {

//     public static OrderDTO createOrderDTO() {

//         OrderDTO orderDTO = new OrderDTO(List.of(1L), 1L, 1);

//         return orderDTO;
//     }

//     public static OrderDTO createOrderDTOWithInvalidUserId() {
//         Long nonExistingUserId = 0L;

//         OrderDTO orderDTO = new OrderDTO(List.of(1L), nonExistingUserId, 1);
//         return orderDTO;
//     }

//     public static OrderDTO createOrderDTOWithInvalidProductID() {
//         Long nonExistingProductID = 0L;

//         OrderDTO orderDTO = new OrderDTO(List.of(nonExistingProductID), 1L, 1);
//         return orderDTO;
//     }

//     public static OrderDTO createOrderDTOWithoutUserIdField() {
//         OrderDTO orderDTO = new OrderDTO();

//         orderDTO.setQuantity(1);
//         orderDTO.setListProductId(List.of(1L));

//         return orderDTO;
//     }
// }
