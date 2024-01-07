package tipolt.andre.spring.models;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import tipolt.andre.spring.models.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
@Data
public class OrderItemModel implements Serializable{

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;

    public Double subTotal() {
        return this.quantity * this.getId().getProduct().getSpotPrice();
    }
}
