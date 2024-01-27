package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.dtos.ForgotPasswordDTO;
import tipolt.andre.spring.models.EmailModel;
import tipolt.andre.spring.models.UserModel;

@Service
public class AuthService {

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    public UserModel getUserInAuthentication() {

        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {

        UserModel user = userService.findUserByEmail(forgotPasswordDTO.getEmail());

        String token = tokenService.generateToken(user);


        EmailModel emailModel = new EmailModel();

        emailModel.setEmailTo(user.getEmail());
        emailModel.setEmailFrom(this.emailFrom);
        emailModel.setOwnerRef(this.emailFrom);
        emailModel.setText(emailService.getBodyEmail(token));
        emailModel.setSubject("Alteração de Senha");

        emailService.sendEmail(emailModel);
         
        return;

    }
}
