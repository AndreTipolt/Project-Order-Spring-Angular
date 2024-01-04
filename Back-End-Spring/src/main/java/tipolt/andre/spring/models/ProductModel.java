package tipolt.andre.spring.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Double spotPrice;

    @Column(nullable = false)
    private Double fowardPrice;

    @Column(nullable = false)
    private Integer installments;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer pixDiscount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItemModel> item = new HashSet<>();

}
