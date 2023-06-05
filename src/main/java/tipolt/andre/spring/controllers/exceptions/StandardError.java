package tipolt.andre.spring.controllers.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable{
    
    private Long timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    
}