package tipolt.andre.spring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmailEnum.ERROR);

            throw new EmailNotSentException("Email Not Sent");
        }

        return emailRepository.save(emailModel);

    }
}
