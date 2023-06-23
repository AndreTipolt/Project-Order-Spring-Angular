package tipolt.andre.spring.services.factories;

import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;

public class UserServiceFactory {
    
    public static UserInsertDTO createUserInsertDTOValid(){
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");

        return userInsertDTO;
    }

    public static UserInsertDTO createUserInsertDTOWithDiffPasswords(){
        UserInsertDTO userInsertDTO = new UserInsertDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("notCoincidePassword");
        
        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOWithDiffPasswords(){
        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("notCoincidePassword");
        
        return userInsertDTO;
    }

    public static UserUpdateDTO createUserUpdateDTOValid(){
        UserUpdateDTO userInsertDTO = new UserUpdateDTO();
        userInsertDTO.setEmail("foo@bar.com");
        userInsertDTO.setName("foo");
        userInsertDTO.setPassword("password123");
        userInsertDTO.setConfirmPassword("password123");
        
        return userInsertDTO;
    }
}
