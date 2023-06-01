package tipolt.andre.spring.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.controllers.dtos.OrderDTO;
import tipolt.andre.spring.models.OrderItemModel;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.models.pk.OrderItemPK;
import tipolt.andre.spring.repositories.OrderItemRepository;
import tipolt.andre.spring.repositories.OrderRepository;
import tipolt.andre.spring.repositories.ProductRepository;
import tipolt.andre.spring.repositories.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }

    public void save(OrderDTO orderDTO) {

        UserModel userExists = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Sem usuario"));

        OrderModel orderModel = new OrderModel();
        orderModel.setMoment(Instant.now());
        orderModel.setUser(userExists);

        OrderModel order = orderRepository.save(orderModel);

        // List<ProductModel> listProducts = new ArrayList<>();

        // for(String idProduct : orderDTO.getListProductId()){
            
        //     ProductModel product = productRepository.findById(idProduct)
        //         .orElseThrow(() -> new RuntimeException("Id do produto invalido"));
            
        //     listProducts.add(product);
        // }

        ProductModel product = productRepository.findById(orderDTO.getProductId()).orElseThrow(() -> new RuntimeException("Produto naoe encontrado"));
        OrderItemModel orderItem = new OrderItemModel();
        OrderItemPK orderItemPK = new OrderItemPK();

        orderItemPK.setOrder(order);
        orderItemPK.setProduct(product);


        orderItem.setId(orderItemPK);
        orderItem.setQuantity(orderDTO.getQuantity());

        orderItemRepository.save(orderItem);
    }
}
