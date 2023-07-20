package tipolt.andre.spring.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tipolt.andre.spring.dtos.OrderDTO;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.services.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/orders")
    public ResponseEntity<Page<OrderModel>> findAll(Pageable pageable) {

        Page<OrderModel> pageOrders = orderService.findAll(pageable);

        return ResponseEntity.ok().body(pageOrders);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<Void> saveOrder(@RequestBody @Valid OrderDTO orderDTO) {

        orderService.save(orderDTO);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/myorders")
    public ResponseEntity<OrderModel> findAllSelfOrders(Pageable pageable){

        Page<OrderModel> pageOrders = orderService.findAllSelfOrders(pageable);

        return ResponseEntity.ok().body(pageOrders);

    }

}
