package tipolt.andre.spring.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    
    @NotNull(message = "Fill All Fields")
    private String name;

    @NotNull(message = "Fill All Fields")
    @Email(message = "Invalid Email")
    private String email;

    @NotNull(message = "Fill All Fields")
    private String password;

    @NotNull(message = "Fill All Fields")
    private String confirmPassword;
}
