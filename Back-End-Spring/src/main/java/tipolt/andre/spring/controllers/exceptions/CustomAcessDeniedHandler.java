package tipolt.andre.spring.controllers.exceptions;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAcessDeniedHandler implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException { // Error when the user don't send the token

        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Unauthorized", authException.getMessage(), request.getRequestURI());
        String jsonResponse = objectMapper.writeValueAsString(error);

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().print(jsonResponse);
    }

}
