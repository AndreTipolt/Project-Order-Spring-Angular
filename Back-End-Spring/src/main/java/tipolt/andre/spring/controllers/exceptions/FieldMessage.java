package tipolt.andre.spring.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FieldMessage {
    
    private String fieldName;

    private String message;
}
