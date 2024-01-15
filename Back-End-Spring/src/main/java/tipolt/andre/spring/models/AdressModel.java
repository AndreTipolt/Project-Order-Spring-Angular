package tipolt.andre.spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import tipolt.andre.spring.models.enums.TypeAdressEnum;
import tipolt.andre.spring.models.enums.converters.TypeAdressEnumConverter;

@Entity
@Table(name = "tb_adresses")
@Data
public class AdressModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String street;
    
    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = true)
    private String complement;

    @Column(nullable = false)
    private String cep;

    @Convert(converter = TypeAdressEnumConverter.class)
    private TypeAdressEnum typeAdress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}
