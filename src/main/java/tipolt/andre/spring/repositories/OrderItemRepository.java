package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.OrderItemModel;
import tipolt.andre.spring.models.pk.OrderItemPK;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemModel, OrderItemPK>{
    
}
