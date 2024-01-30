package tipolt.andre.spring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import tipolt.andre.spring.exceptions.EmailNotSentException;
import tipolt.andre.spring.models.EmailModel;
import tipolt.andre.spring.models.enums.StatusEmailEnum;
import tipolt.andre.spring.repositories.EmailRepository;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) {

        emailModel.setSendDateEmail(LocalDateTime.now());

        try {

            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            messageHelper.setFrom(emailModel.getEmailFrom());
            messageHelper.setTo(emailModel.getEmailTo());
            messageHelper.setSubject(emailModel.getSubject());
            messageHelper.setText(emailModel.getText(), true);

            emailSender.send(mimeMessage);

            emailModel.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException | MessagingException e) {

            emailModel.setStatusEmail(StatusEmailEnum.ERROR);
            throw new EmailNotSentException("Email Not Sent");
        }

        return emailRepository.save(emailModel);

    }

    public String getBodyEmail(String token){
        return "<!DOCTYPE html> " +
        "<html lang='en'> " +
        "<head> " +
          "<meta charset='UTF-8'> " +
          "<meta name='viewport' content='width=device-width, initial-scale=1.0'> " +
          "<style> " +
            "body { " +
              "font-family: Arial, sans-serif; " +
              "background-color: #f4f4f4; " +
              "margin: 0; " +
              "padding: 0; " +
              "display: flex; " +
              "justify-content: center; " +
              "align-items: center; " +
              "height: 100vh; " +
            "}" +
        
            ".email-container { " +
              "max-width: 100%; " +
              "background-color: #ffffff; " +
              "padding: 20px; " +
              "border-radius: 8px; " +
              "box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); " +
            "}" +

            ".email-container p{ " +
            "color: inherit;" +
            "}" +
            ".button { " +
              "display: inline-block; " +
              "padding: 10px 20px; " +
              "background-color: #272D2D; " +
              "color: #fff; " +
              "text-decoration: none; " +
              "border-radius: 5px; " +
              "font-size: 16px; " +
            "} " +
          "</style> " +
        "</head> " +
        "<body> " +
        
          "<div class='email-container'> " +
            "<h2>Olá,</h2> " +
            "<p>Recebemos uma notificação de atualização de senha em sua conta. Clique no link para atualizar sua senha.</p> " +
    
            "<a href='"+ "http://localhost:4200/auth/forgot-password/change-password?token="+ token +"' class='button'>Atualizar senha</a>" +
          "</div> " +
        
        "</body> " +
        "</html>";
    }
}
