package tipolt.andre.spring.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import tipolt.andre.spring.models.enums.StatusOrder;

@Entity
@Table(name = "tb_order")
@Data
public class OrderModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserModel user;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItemModel> listOrderItems = new HashSet<>();

    private StatusOrder status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentModel payment;

    public Double getTotal() {
        Double sumTotal = 0.0;

        for (OrderItemModel orderItem : listOrderItems) {
            sumTotal += orderItem.subTotal();
        }
        return sumTotal;
    }
}
