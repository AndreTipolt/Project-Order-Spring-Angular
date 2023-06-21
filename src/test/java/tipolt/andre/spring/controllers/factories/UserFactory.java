package tipolt.andre.spring.controllers.factories;

import tipolt.andre.spring.dtos.UserDTO;

public class UserFactory {
    

    public static UserDTO createUserDTO(){

        UserDTO userDTO = new UserDTO("Foo", "bar@gmail.com", "1234", "1234");
        return userDTO;
    }

    public static UserDTO createUserDTOWithoutEmailField() {
        UserDTO userDTO = new UserDTO();

        userDTO.setName("Foo");
        userDTO.setPassword("1234");
        userDTO.setConfirmPassword("1234");

        return userDTO;
    }

    public static UserDTO createUserWithDiffPasswords() {

        UserDTO userDTO = new UserDTO();

        userDTO.setName("Foo");
        userDTO.setEmail("bar@gmail.com");
        userDTO.setPassword("1234");
        userDTO.setConfirmPassword("diffpassword");

        return userDTO;
    }

    public static UserDTO createUserWithEmailFieldAlreadyExists() {
        UserDTO userDTO = new UserDTO("Foo", "andretipoltlopes@gmail.com", "1234", "1234");
        return userDTO;
    }
}
