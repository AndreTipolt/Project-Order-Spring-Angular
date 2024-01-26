package tipolt.andre.spring.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tipolt.andre.spring.models.NotificationModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataHeaderDTO {
    
    private List<NotificationModel> listNotifications;

    private String imageUser;
}
