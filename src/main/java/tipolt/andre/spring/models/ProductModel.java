package tipolt.andre.spring.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "tb_product")
@Data
public class ProductModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItemModel> item = new HashSet<>();

}
