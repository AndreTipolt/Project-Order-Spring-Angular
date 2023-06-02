package tipolt.andre.spring.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import tipolt.andre.spring.models.pk.OrderItemPK;

@Entity
@Table(name = "tb_order_item")
@Data
public class OrderItemModel {
    
    @EmbeddedId
    private OrderItemPK id  = new OrderItemPK();

    private Integer quantity;
}
