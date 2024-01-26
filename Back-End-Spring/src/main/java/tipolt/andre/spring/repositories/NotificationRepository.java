package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.NotificationModel;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel, String> {

}
