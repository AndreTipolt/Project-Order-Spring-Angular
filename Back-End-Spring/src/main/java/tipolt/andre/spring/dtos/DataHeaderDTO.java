package tipolt.andre.spring.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tipolt.andre.spring.models.NotificationUserModel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataHeaderDTO {
    
    private List<NotificationUserModel> listNotifications;

    private String imageUser;
}
