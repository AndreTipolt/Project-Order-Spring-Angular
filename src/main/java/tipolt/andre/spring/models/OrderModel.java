package tipolt.andre.spring.models;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_order")
@Data
public class OrderModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserModel user;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItemModel> listOrderItems = new HashSet<>();
}
