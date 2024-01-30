package tipolt.andre.spring.dtos;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadNotificationsDTO {
    
    @NotEmpty(message = "List id notifications doesn't accept empty list")
    private List<String> listIdNotifications;
}
