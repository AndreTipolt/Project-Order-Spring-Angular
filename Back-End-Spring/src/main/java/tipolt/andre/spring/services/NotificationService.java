package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.dtos.ReadNotificationsDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.NotificationModel;
import tipolt.andre.spring.models.NotificationUserModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.models.pk.NotificationUserPK;
import tipolt.andre.spring.repositories.NotificationRepository;
import tipolt.andre.spring.repositories.NotificationUserRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationUserRepository notificationUserRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public List<NotificationUserModel> findAllNotificationsByUser() {

        UserModel user = authService.getUserInAuthentication();

        // List<NotificationModel> listNotification =
        // notificationRepository.findAllNotificationsByUserId(user.getId())
        // .orElse(List.of());

        List<NotificationUserModel> listNotificationUser = notificationUserRepository
                .findAllNotificationsByUser(user.getId())
                .orElse(List.of());

        return listNotificationUser;
    }

    public NotificationModel findNotificationByIdOrThrowException(String notificationId) {

        if (notificationId == null) {

            throw new ObjectNotFoundException("Notification Id doesn't exists");

        }

        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ObjectNotFoundException("Notification Id doesn't exists"));
    }

    public void readNotifications(ReadNotificationsDTO readNotificationsDTO) {

        readNotificationsDTO.getListIdNotifications().stream().forEach((String notificationId) -> {

            NotificationModel notificationModel = findNotificationByIdOrThrowException(notificationId); // Search notification

            UserModel user = authService.getUserInAuthentication();

            
            NotificationUserPK notificationUserPK = new NotificationUserPK(); // Create a pk to search

            notificationUserPK.setUser(user);
            notificationUserPK.setNotification(notificationModel);

            NotificationUserModel notificationUserModel = notificationUserRepository.findById(notificationUserPK).orElseThrow(() -> new ObjectNotFoundException("Notification User Model that not exists"));

            notificationUserModel.setRead(true);

            notificationUserRepository.save(notificationUserModel);
        });
        
        return;
    }
}
