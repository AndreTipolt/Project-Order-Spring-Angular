package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.dtos.UserDTO;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public void saveUser(UserDTO userDTO){
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new PasswordNotCoincideException("Password not Coincide");
        }

        UserModel newUser = new UserModel();

        String newPassword = passwordEncoder.encode(userDTO.getPassword());

        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(newPassword);

        userRepository.save(newUser);
    }
}
