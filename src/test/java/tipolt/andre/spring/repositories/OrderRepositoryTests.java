package tipolt.andre.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tipolt.andre.spring.models.OrderModel;

@DataJpaTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    private String existingId;
    private String notExistingId;

    @BeforeEach
    public void setUp() {
        this.existingId = "1";
        this.notExistingId = "0";
    }

    @Test
    @DisplayName("Find All Should Return All Orders")
    public void findAllShouldReturnAllOrders() {

        List<OrderModel> listOrders = orderRepository.findAll();

        Assertions.assertFalse(listOrders.isEmpty());
    }

    @Test
    @DisplayName("Find by id should return optional order when id exists")
    public void findByIdShouldReturnOptionalOrderWhenIdExists() {

        Optional<OrderModel> order = orderRepository.findById(existingId);

        Assertions.assertTrue(order.isPresent());
    }

    @Test
    @DisplayName("Find by id should return empty order when id does not exists")
    public void findByIdSHouldReturnEmptyOrderWhenIdDoesNotExists() {

        Optional<OrderModel> order = orderRepository.findById(notExistingId);

        Assertions.assertTrue(order.isEmpty());
    }

}
