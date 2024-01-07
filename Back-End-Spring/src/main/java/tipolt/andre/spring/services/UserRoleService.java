package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.RoleModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.models.UserRoleModel;
import tipolt.andre.spring.models.pk.UserRolePK;
import tipolt.andre.spring.repositories.RoleRepository;
import tipolt.andre.spring.repositories.UserRoleRepository;

@Service
public class UserRoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public void insertManyUserRoles(List<String> listOfRoleID, UserModel userCadastred){
        for(String roleId : listOfRoleID){
            RoleModel role = roleRepository.findById(roleId)
                                           .orElseThrow(() -> new ObjectNotFoundException("Role Id not Found"));
            
            UserRolePK userRolePK = new UserRolePK();
            userRolePK.setRole(role);
            userRolePK.setUser(userCadastred);

            UserRoleModel userRoleModel = new UserRoleModel();
            userRoleModel.setId(userRolePK);

            userRoleRepository.save(userRoleModel);
        }
    }
}
