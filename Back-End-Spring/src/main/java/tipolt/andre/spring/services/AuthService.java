package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.dtos.ForgotPasswordDTO;
import tipolt.andre.spring.models.UserModel;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public UserModel getUserInAuthentication(){

        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String forgotPassword(ForgotPasswordDTO forgotPasswordDTO){

        UserModel user = userService.findUserByEmail(forgotPasswordDTO.getEmail());

        String token = tokenService.generateToken(user);

        return token;
    }
}
