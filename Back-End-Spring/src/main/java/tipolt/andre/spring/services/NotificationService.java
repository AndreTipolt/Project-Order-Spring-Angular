package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.models.NotificationUserModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.NotificationRepository;
import tipolt.andre.spring.repositories.NotificationUserRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public List<NotificationUserModel> findAllNotificationsByUser(){

        UserModel user = authService.getUserInAuthentication();
        
        List<NotificationUserModel> listNotification = notificationUserRepository.findAllByUser(user.getId())
                                                                    .orElse(List.of());

        return listNotification;
    }
}
