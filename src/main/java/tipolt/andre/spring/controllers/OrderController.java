package tipolt.andre.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import tipolt.andre.spring.controllers.utils.ObjectMapperUtils;
import tipolt.andre.spring.dtos.OrderDTO;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.services.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @GetMapping(value = "/orders")
    public Object findAll(Pageable pageable) throws JsonMappingException, JsonProcessingException {

        JsonNode cachedRedisFindAllOrders = objectMapperUtils.getRedisKeyAndConvertToJsonNode("orders_findAll");

        if(cachedRedisFindAllOrders != null){
            return ResponseEntity.ok().body(cachedRedisFindAllOrders);
        }

        Page<OrderModel> pageOrders = orderService.findAll(pageable);

        objectMapperUtils.convertObjectToStringAndSaveInRedis("orders_findAll", pageOrders);

        return ResponseEntity.ok().body(pageOrders);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<Void> saveOrder(@RequestBody @Valid OrderDTO orderDTO) {

        orderService.save(orderDTO);

        return ResponseEntity.noContent().build();
    }

}
