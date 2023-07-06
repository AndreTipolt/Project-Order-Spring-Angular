package tipolt.andre.spring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tb_role")
@Data
public class RoleModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String authority;
}
