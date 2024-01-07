package tipolt.andre.spring.models;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;
import tipolt.andre.spring.models.pk.UserRolePK;

@Entity
@Table(name = "tb_user_role")
@Setter
public class UserRoleModel implements Serializable{

    @JsonIgnore
    @EmbeddedId
    private UserRolePK id = new UserRolePK();

    public RoleModel getRole(){
        return id.getRole();
    }

    @JsonIgnore
    public UserModel getUser(){
        return id.getUser();
    }

}
