package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.models.NotificationModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.NotificationRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public List<NotificationModel> findAllNotificationsByUser(){

        UserModel user = authService.getUserInAuthentication();
        
        List<NotificationModel> listNotification = notificationRepository.findAllNotificationsByUserId(user.getId())
                                                                    .orElse(List.of());

        return listNotification;
    }
}
