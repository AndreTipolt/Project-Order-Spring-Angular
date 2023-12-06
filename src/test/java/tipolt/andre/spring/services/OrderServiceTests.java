package tipolt.andre.spring.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.OrderDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.repositories.OrderRepository;

public class OrderServiceTests extends ApplicationTestConfig {

    @Autowired
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    // @Test
    // @DisplayName("Find all should return all orders")
    // public void findAllShouldReturnAllOrders() {

    //     List<OrderModel> listOrders = orderService.findAll();

    //     Assertions.assertFalse(listOrders.isEmpty());
    // }

    @Test
    @DisplayName("Save Order Should Throw ObjectNotFoundException When userID does not exists")
    public void saveOrderShouldThrowObjectNotFoundExceptioWhenUserIdDoesNotExists() {

        OrderDTO orderDTOWithUserIdNotExisting = new OrderDTO(List.of(1L), 0L, 1);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            orderService.save(orderDTOWithUserIdNotExisting);
        });
    }

    @Test
    @DisplayName("Save Order Should Throw ObjectNotFoundException When productID does not exists")
    public void saveOrderShouldThrowObjectNotFoundExceptionWhenProductIdDoesNotExists() {

        OrderDTO orderDTOWithProductIdNotExisting = new OrderDTO(List.of(0L), 1L, 1);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            orderService.save(orderDTOWithProductIdNotExisting);
        });
    }
}
