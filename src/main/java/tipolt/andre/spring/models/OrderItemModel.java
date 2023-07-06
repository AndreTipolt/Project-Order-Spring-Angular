package tipolt.andre.spring.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import tipolt.andre.spring.models.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
@Data
public class OrderItemModel {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;

    public Double subTotal() {
        return this.quantity * this.getId().getProduct().getPrice();
    }
}
