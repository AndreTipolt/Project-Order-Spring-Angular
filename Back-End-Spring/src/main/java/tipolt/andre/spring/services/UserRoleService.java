package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void insertManyUserRoles(List<RoleModel> listRole, UserModel userCadastred){
        for(RoleModel role : listRole){
            // RoleModel role = roleRepository.findById(roleId)
            //                                .orElseThrow(() -> new ObjectNotFoundException("Role Id not Found"));
            
            UserRolePK userRolePK = new UserRolePK();
            userRolePK.setRole(role);
            userRolePK.setUser(userCadastred);

            UserRoleModel userRoleModel = new UserRoleModel();
            userRoleModel.setId(userRolePK);

            userRoleRepository.save(userRoleModel);
        }
    }

    @Transactional(readOnly = true)
    public RoleModel getRoleAdmin(){

        return roleRepository.findById("3d041437-412f-4466-aa3b-72da1e19e52a").get();
    }

    @Transactional(readOnly = true)
    public RoleModel getRoleUser(){

        return roleRepository.findById("59bd2375-dd0c-4570-af65-f36a138efc94").get();
    }
}
