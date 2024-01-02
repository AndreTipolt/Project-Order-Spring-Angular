package tipolt.andre.spring.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthCustomError {
    
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;
}
