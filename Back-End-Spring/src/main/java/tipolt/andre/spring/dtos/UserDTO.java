package tipolt.andre.spring.dtos;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Fill Name Field")
    private String name;

    @NotBlank(message = "Fill Email Field")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Fill Password Field")
    private String password;

    @NotBlank(message = "Fill Confirm Password Field")
    private String confirmPassword;

    @NotEmpty(message = "Fill roles Field")
    private List<String> roles;
}
