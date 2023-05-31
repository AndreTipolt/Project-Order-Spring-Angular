package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import tipolt.andre.spring.controllers.dtos.OrderDTO;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderModel> findAll(){
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> saveOrder(@RequestBody OrderDTO orderDTO){

        orderService.save(orderDTO);

        return ResponseEntity.status(201).body(null);
    }
    
}
