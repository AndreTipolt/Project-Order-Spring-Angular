package tipolt.andre.spring.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tipolt.andre.spring.models.pk.NotificationUserPK;

@Entity
@Table(name = "tb_notification_user")
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUserModel {

    @EmbeddedId
    private NotificationUserPK id = new NotificationUserPK();

    @Column(nullable = false)
    private Boolean read;


    public NotificationModel getNotification(){
        return id.getNotification();
    }

    public Boolean getRead(){
        return read;
    }
}
