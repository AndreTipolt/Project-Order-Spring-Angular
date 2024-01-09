package tipolt.andre.spring.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_category")
@Data
public class CategoryModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductModel> listProducts = new ArrayList<>();
}
