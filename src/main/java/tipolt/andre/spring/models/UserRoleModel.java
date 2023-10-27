package tipolt.andre.spring.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Setter;
import tipolt.andre.spring.models.pk.UserRolePK;

@Entity
@Table(name = "tb_user_role")
@Setter
public class UserRoleModel {

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
