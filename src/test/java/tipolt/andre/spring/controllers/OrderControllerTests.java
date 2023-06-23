package tipolt.andre.spring.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.OrderDTO;
import tipolt.andre.spring.factories.OrderFactory;
import tipolt.andre.spring.repositories.OrderRepository;
import tipolt.andre.spring.services.OrderService;

public class OrderControllerTests extends ApplicationTestConfig {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Find All should return list of orders")
    public void findAllShouldReturnListOfOrders() throws Exception {

        ResultActions result = mockMvc.perform(get("/orders")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Save Order should return not found when userId does not exists")
    public void saveOrderShouldReturnNotFoundWhenUserIdDoesNotExists() throws Exception {

        OrderDTO orderWithInvalidUserId = OrderFactory.createOrderDTOWithInvalidUserId();

        String jsonBody = objectMapper.writeValueAsString(orderWithInvalidUserId);

        ResultActions result = mockMvc.perform(post("/orders")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Save Order should return not found when someone productId does not exists")
    public void saveOrderShouldReturnNotFoundWhenSomeoneProductIdDoesNotExists() throws Exception {

        OrderDTO orderWithInvalidProductId = OrderFactory.createOrderDTOWithInvalidProductID();

        String jsonBody = objectMapper.writeValueAsString(orderWithInvalidProductId);

        ResultActions result = mockMvc.perform(post("/orders")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("save order should return unprocessable entity when someone field is missing")
    public void saveOrderShouldReturnUnprocessableEntityWhenSomeoneFieldIsMissing() throws Exception {
        OrderDTO orderDTOWithoutUserIdField = OrderFactory.createOrderDTOWithoutUserIdField();

        String jsonBody = objectMapper.writeValueAsString(orderDTOWithoutUserIdField);

        ResultActions result = mockMvc.perform(post("/orders")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Save Order should return created when orderDTO is valid")
    public void saveOrderShouldReturnCreatedWhenOrderDTOIsValid() throws Exception{

        OrderDTO orderDTO = OrderFactory.createOrderDTO();

        String jsonBody = objectMapper.writeValueAsString(orderDTO);

        ResultActions result = mockMvc.perform(post("/orders")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }
}
