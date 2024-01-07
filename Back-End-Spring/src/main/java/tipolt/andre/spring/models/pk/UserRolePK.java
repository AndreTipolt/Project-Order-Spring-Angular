package tipolt.andre.spring.models.pk;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import tipolt.andre.spring.models.RoleModel;
import tipolt.andre.spring.models.UserModel;

@Embeddable
@Data
public class UserRolePK implements Serializable{
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleModel role;
}
