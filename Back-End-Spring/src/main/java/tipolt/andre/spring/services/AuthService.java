package tipolt.andre.spring.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.models.UserModel;

@Service
public class AuthService {


    public UserModel getUserInAuthentication(){

        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
