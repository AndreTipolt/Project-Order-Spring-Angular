package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;
import tipolt.andre.spring.models.RoleModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.models.UserRoleModel;
import tipolt.andre.spring.models.pk.UserRolePK;
import tipolt.andre.spring.repositories.RoleRepository;
import tipolt.andre.spring.repositories.UserRepository;
import tipolt.andre.spring.repositories.UserRoleRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(UserInsertDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new PasswordNotCoincideException("Password not Coincide");
        }

        UserModel newUser = new UserModel();

        String newPassword = passwordEncoder.encode(userDTO.getPassword());

        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(newPassword);

        UserModel createdUser = userRepository.save(newUser);

        for(Long roleId : userDTO.getRoles()){

            RoleModel role = roleRepository.findById(roleId)
                                           .orElseThrow(() -> new ObjectNotFoundException("Role Id not Found"));
            
            UserRolePK userRolePK = new UserRolePK();
            userRolePK.setRole(role);
            userRolePK.setUser(createdUser);

            UserRoleModel userRoleModel = new UserRoleModel();
            userRoleModel.setId(userRolePK);

            userRoleRepository.save(userRoleModel);
        }
    }

    public UserModel findUserById(String userId) {

        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User Not Found"));
    }

    public void updateUser(String userId, UserUpdateDTO userUpdateDTO) {

        UserModel user = this.findUserById(userId);

        if (!userUpdateDTO.getPassword().equals(userUpdateDTO.getConfirmPassword())) {
            throw new PasswordNotCoincideException("Password not coincides");
        }
        updateUserDtoToUserDatabase(user, userUpdateDTO);

        userRepository.save(user);
    }

    private void updateUserDtoToUserDatabase(UserModel userModel, UserUpdateDTO userUpdateDTO) {
        userModel.setEmail(userUpdateDTO.getEmail());
        userModel.setName(userUpdateDTO.getName());
        userModel.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
    }
}
