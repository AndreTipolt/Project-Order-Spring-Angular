package tipolt.andre.spring.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.NotificationUserModel;
import tipolt.andre.spring.models.pk.NotificationUserPK;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUserModel, NotificationUserPK>{
    
    @Query(nativeQuery = true, value = "SELECT * from tb_notification_user notification_user WHERE notification_user.user_id  = :userId")
    Optional<List<NotificationUserModel>> findAllNotificationsByUser(String userId);
}
