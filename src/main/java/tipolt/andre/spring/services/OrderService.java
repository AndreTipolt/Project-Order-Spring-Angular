package tipolt.andre.spring.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.dtos.OrderDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.OrderItemModel;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.models.enums.StatusOrder;
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

    @Transactional(readOnly = true)
    public Page<OrderModel> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public void save(OrderDTO orderDTO) {

        UserModel userExists = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("User not Found"));

        OrderModel orderModel = new OrderModel();
        orderModel.setMoment(Instant.now());
        orderModel.setUser(userExists);
        orderModel.setStatus(StatusOrder.WAIT_PAYMENT);

        OrderModel order = orderRepository.save(orderModel);

        List<OrderItemModel> listOrderItem = new ArrayList<>();

        for (String productId : orderDTO.getListProductId()) {

            ProductModel product = productRepository.findById(productId)
                    .orElseThrow(() -> new ObjectNotFoundException("Product Not Found"));

            OrderItemPK orderItemPK = new OrderItemPK();

            orderItemPK.setOrder(order);

            OrderItemModel orderItem = new OrderItemModel();

            orderItemPK.setProduct(product);
            orderItem.setId(orderItemPK);
            orderItem.setQuantity(1);

            listOrderItem.add(orderItem);
        }

        for (OrderItemModel orderItem : listOrderItem) {
            orderItemRepository.save(orderItem);
        }
    }

    @Transactional(readOnly = true)
    public Page<OrderModel> findAllSelfOrders(Pageable pageable){



    }
}
