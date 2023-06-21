package tipolt.andre.spring.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_payment")
@Data
public class PaymentModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Instant moment;

    @JsonIgnore
    @OneToOne
    @MapsId
    private OrderModel order;
}
