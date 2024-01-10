package tipolt.andre.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.validation.Valid;
import tipolt.andre.spring.controllers.utils.ObjectMapperUtils;
import tipolt.andre.spring.dtos.OrderDTO;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @GetMapping
    public ResponseEntity<? extends Object> findAll() throws JsonMappingException, JsonProcessingException {

        JsonNode cachedRedisFindAllOrders = objectMapperUtils.getRedisKeyAndConvertToJsonNode("orders_findAll");

        if (cachedRedisFindAllOrders != null) {
            return ResponseEntity.ok().body(cachedRedisFindAllOrders);
        }

        UserModel user = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<OrderModel> pageOrders = orderService.findAllOrders(user.getId());

        // objectMapperUtils.convertObjectToStringAndSaveInRedis("orders_findAll", pageOrders);

        return ResponseEntity.ok().body(pageOrders);
    }

    @PostMapping
    public ResponseEntity<Void> saveOrder(@RequestBody @Valid OrderDTO orderDTO) {

        orderService.save(orderDTO);

        return ResponseEntity.noContent().build();
    }

}
