package tipolt.andre.spring.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDTO {

    @NotNull(message = "Fill the email field")
    private String email;

    @NotNull(message = "Fill the password field")
    private String password;
}
