package tipolt.andre.spring.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.UserDTO;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

public class UserServiceTests extends ApplicationTestConfig{
    
    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Find All Users should return list of users")
    public void findAllUsersShouldReturnsListOfUsers() {
        
        List<UserModel> listUser = userService.findAll();

        Assertions.assertFalse(listUser.isEmpty());

    }

    @Test
    @DisplayName("Save User should save user when userDTO is valid")
    public void saveUserShouldSaveUserWhenUserDTOIsValid() {

        UserDTO validUserDTO = new UserDTO("Lucas", "lucas@gmail.com", "senha1234", "senha1234");

        Assertions.assertDoesNotThrow(() -> {
            userService.saveUser(validUserDTO);
        });
    }

    @Test
    @DisplayName("Save User Should Throw PasswordNotCoincideException when password does not coincides")
    public void saveUserShouldThrowPasswordNotCoincideExceptionWhenPasswordDoesNotCoincides() {

        UserDTO userDTOWithDiffPasswords = new UserDTO("Lucas", "lucas@gmail.com", "password", "notpasswordcoincide");

        Assertions.assertThrows(PasswordNotCoincideException.class, () -> {

            userService.saveUser(userDTOWithDiffPasswords);
        });
    }


    
}
