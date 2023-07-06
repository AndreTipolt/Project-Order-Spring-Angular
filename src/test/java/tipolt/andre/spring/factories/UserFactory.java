package tipolt.andre.spring.factories;

import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;

public class UserFactory {

    public static UserInsertDTO createUserInsertDTOValid() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");

        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithDiffPasswords() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("notCoincidePassword");

        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithoutFieldEmail() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");

        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithEmailFieldThatAlreadyExists() {
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setName("foo");
        userInsertDTO.setEmail("luanarodrigues@gmail.com");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOWithDiffPasswords() {
        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("notCoincidePassword");

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOValid() {
        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setEmail("emailvalid@email.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");

        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateWithEmailNotBelongsUserId() {

        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setName("foo");
        userInsertDTO.setEmail("luanarodrigues@gmail.com");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");

        return userInsertDTO;
    }
}
