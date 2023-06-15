package tipolt.andre.spring.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tipolt.andre.spring.services.validation.UserInsertValid;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UserInsertValid
public class UserDTO {
    
    @NotNull(message = "Fill Name Field")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    @NotNull(message = "Fill Password Field")
    private String password;

    @NotNull(message = "Fill Confirm Password Field")
    private String confirmPassword;
}
