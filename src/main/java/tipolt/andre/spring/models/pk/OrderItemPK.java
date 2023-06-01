package tipolt.andre.spring.models.pk;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tipolt.andre.spring.models.OrderModel;
import tipolt.andre.spring.models.ProductModel;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPK {
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;
}
