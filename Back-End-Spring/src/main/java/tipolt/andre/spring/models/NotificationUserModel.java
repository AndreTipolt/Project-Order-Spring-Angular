package tipolt.andre.spring.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tipolt.andre.spring.models.pk.NotificationUserPK;

@Entity
@Table(name = "tb_notification_user")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUserModel {

    @EmbeddedId
    private NotificationUserPK id = new NotificationUserPK();

    public NotificationModel getNotification() {

        return id.getNotification();
    }
}
