package tipolt.andre.spring.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Fill Email Field")
    private String email;

    @NotBlank(message = "Fill Password Field")
    private String password;
}
