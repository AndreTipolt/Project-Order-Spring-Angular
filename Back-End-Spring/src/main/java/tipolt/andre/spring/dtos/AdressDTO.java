package tipolt.andre.spring.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdressDTO {

    @NotBlank(message = "Fill Street Field")
    private String street;
    
    @NotBlank(message = "Fill Number Field")
    private String number;

    @NotBlank(message = "Fill Neighborhood Field")
    private String neighborhood;

    @NotBlank(message = "Fill City Field")
    private String city;

    @NotBlank(message = "Fill State Field")
    private String state;

    @NotBlank(message = "Fill Cep Field")
    private String cep;

    @NotBlank(message = "Fill typeAdress Field")
    @Pattern(regexp = "WORK|HOME")
    private String typeAdress;

    @NotBlank(message = "Fill Email Field")
    private String userId;

    private String complement;
}
