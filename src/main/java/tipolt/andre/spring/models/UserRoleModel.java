package tipolt.andre.spring.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import tipolt.andre.spring.models.pk.UserRolePK;

@Entity
@Table(name = "tb_user_role")
@Data
public class UserRoleModel {

    @EmbeddedId
    private UserRolePK id = new UserRolePK();

    public RoleModel getRole(){
        return id.getRole();
    }

    public UserModel getUser(){
        return id.getUser();
    }

}
