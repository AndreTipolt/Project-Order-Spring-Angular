package tipolt.andre.spring.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tipolt.andre.spring.controllers.exceptions.StandardError;
import tipolt.andre.spring.exceptions.InvalidJWTException;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;
import tipolt.andre.spring.services.TokenService;

@Component
public class SecurityFilterConfig extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenAuthHeader = getTokenFromRequest(request);

        if (tokenAuthHeader != null) {

            try {
                String userId = tokenService.validateToken(tokenAuthHeader);

                if (userId == null) { // Invalid token
                    throw new InvalidJWTException("Invalid Token");
                }

                UserModel user = userRepository.findById(userId)
                        .orElseThrow(() -> new InvalidJWTException("Invalid JWT Exception")); // Token with userId is invalid

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (InvalidJWTException e) {

                String error = createInvalidJWTObject(request);

                response.setContentType("application/json");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().print(error);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null)
            return null;

        return authHeader.replace("Bearer ", "");
    }

    private String createInvalidJWTObject(HttpServletRequest httpServletRequest){

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Invalid Token", "The token is invalid", httpServletRequest.getRequestURI());

        try {
            return objectMapper.writeValueAsString(err);
        } catch (JsonProcessingException e) {
            
            throw new RuntimeException("Error to create a object Invalid JWT");
        }
    }
}
