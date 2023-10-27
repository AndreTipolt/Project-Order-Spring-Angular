package tipolt.andre.spring.factories;

import java.util.List;

import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;

public class UserFactory {

    public static UserInsertDTO createUserInsertDTOValid() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");
        userInsertDTO.setRoles(List.of("1"));

        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithDiffPasswords() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("notCoincidePassword");
        userInsertDTO.setRoles(List.of("1"));

        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithoutFieldEmail() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setEmail(null);
        userInsertDTO.setConfirmPassword("password123");
        userInsertDTO.setRoles(List.of("1"));
        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithEmailFieldThatAlreadyExists() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setName("foo");
        userInsertDTO.setEmail("andretipoltlopes@gmail.com");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");
        userInsertDTO.setRoles(List.of("1"));

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOWithDiffPasswords() {
        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("notCoincidePassword");
        userInsertDTO.setRoles(List.of("1"));

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOValid() {
        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setEmail("emailvalid@email.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");
        userInsertDTO.setRoles(List.of("1"));

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateWithEmailNotBelongsUserId() {

        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setName("foo");
        userInsertDTO.setEmail("luanarodrigues@gmail.com");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");
        userInsertDTO.setRoles(List.of("1"));

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOWhenIdUserDoesNotExist(){

        UserUpdateDTO userUpdateDTOValid = new UserUpdateDTO();
        userUpdateDTOValid.setEmail("emailvalid@gmail.com");
        userUpdateDTOValid.setName("Random name");
        userUpdateDTOValid.setPassword("randomPassword");
        userUpdateDTOValid.setConfirmPassword("randomPassword");
        userUpdateDTOValid.setRoles(List.of("1"));
        return userUpdateDTOValid;
        
    }
}
