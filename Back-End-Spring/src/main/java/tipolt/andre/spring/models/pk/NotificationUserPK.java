package tipolt.andre.spring.models.pk;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import tipolt.andre.spring.models.NotificationModel;
import tipolt.andre.spring.models.UserModel;

@Embeddable
@Data
public class NotificationUserPK implements Serializable{
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "notification_id")
    private NotificationModel notification;
}
