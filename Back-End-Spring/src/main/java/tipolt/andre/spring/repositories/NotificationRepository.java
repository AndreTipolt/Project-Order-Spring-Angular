package tipolt.andre.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.NotificationModel;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, String> {

    @Query(nativeQuery = true, value = 
    "SELECT notifications.* FROM tb_notification notifications " +
    "INNER JOIN tb_notification_user notification_user " +
    "ON notification_user.notification_id = notifications.id " +
    "AND notification_user.user_id = :userId " +
    "AND notification_user.read = false")
    Optional<List<NotificationModel>> findAllNotificationsByUserId(String userId);
}
