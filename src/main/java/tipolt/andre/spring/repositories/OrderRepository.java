package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
