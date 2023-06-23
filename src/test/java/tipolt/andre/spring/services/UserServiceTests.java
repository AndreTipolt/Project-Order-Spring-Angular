package tipolt.andre.spring.services;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.exceptions.PasswordNotCoincideException;
import tipolt.andre.spring.factories.UserFactory;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;

public class UserServiceTests extends ApplicationTestConfig{
    
    @Autowired
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private String existingId;
    private String nonExistingId;


    @BeforeEach
    public void setUp() {
        this.existingId = "1";
        this.nonExistingId = "0";
    }

    @Test
    @DisplayName("Find All Users should return list of users")
    public void findAllUsersShouldReturnsListOfUsers() {
        
        List<UserModel> listUser = userService.findAll();

        Assertions.assertFalse(listUser.isEmpty());

    }

    @Test
    @DisplayName("Save User Should Throw PasswordNotCoincideException when password does not coincides")
    public void saveUserShouldThrowPasswordNotCoincideExceptionWhenPasswordDoesNotCoincides() {

        UserInsertDTO userDTOWithDiffPasswords = UserFactory.createUserInsertDTOWithDiffPasswords();

        Assertions.assertThrows(PasswordNotCoincideException.class, () -> {

            userService.saveUser(userDTOWithDiffPasswords);
        });
    }

    @Test
    @DisplayName("Save User should save user when userInsertDTO is valid")
    public void saveUserShouldSaveUserWhenUserInsertDTOIsValid() {

        UserInsertDTO validUserDTO = UserFactory.createUserInsertDTOValid();

        validUserDTO.setEmail("emailvalid@gmail.com"); // It's for don't throw email already exiests

        Assertions.assertDoesNotThrow(() -> {
            userService.saveUser(validUserDTO);
        });
    }

    @Test
    @DisplayName("Update User should throw PasswordNotCoincideException when passwords does not coincides")
    public void updateUseSholdThrowPasswordNotCoincideExceptionWhenPasswordsDoesNotCoincides() {

        UserUpdateDTO userDTOWithDiffPasswords = UserFactory.createUserUpdateDTOWithDiffPasswords();

        Assertions.assertThrows(PasswordNotCoincideException.class, () -> {

            userService.updateUser(existingId, userDTOWithDiffPasswords);
        });
    }

    @Test
    @DisplayName("Update User should throw ObjectNotFoundException when user id does not exists")
    public void updateUseSholdThrowObjectNotFoundExceptionWhenUserIdDoesNotExists() {

        UserUpdateDTO validUserUpdateDTO = UserFactory.createUserUpdateDTOValid();

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            userService.updateUser(nonExistingId, validUserUpdateDTO);
        });
    }

    @Test
    @DisplayName("Update User should save user when userUpdateDTO is valid")
    public void updateUserShouldUpdateUserWhenUserUpdateDTOIsValid() {

        UserUpdateDTO validUserUpdateDTO = UserFactory.createUserUpdateDTOValid();

        Assertions.assertDoesNotThrow(() -> {
            userService.updateUser(existingId, validUserUpdateDTO);
        });
    }
}
