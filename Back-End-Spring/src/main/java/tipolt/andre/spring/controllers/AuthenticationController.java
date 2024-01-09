package tipolt.andre.spring.controllers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tipolt.andre.spring.dtos.AuthenticationDTO;
import tipolt.andre.spring.dtos.LoginResponseDTO;
import tipolt.andre.spring.exceptions.BadCredentialsException;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.TokenService;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Value("${jwt.duration}")
    private Long duration;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private final String TOKEN_PREFIX = "Bearer";

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.getEmail(),
                authenticationDTO.getPassword());

        try {

            Authentication auth = this.authenticationManager.authenticate(usernamePassword); // Do the authentication
            String acessToken = tokenService.generateToken((UserModel) auth.getPrincipal());

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

            Instant dateExpires = LocalDateTime.now().plusSeconds(duration).toInstant(ZoneOffset.of("-03:00"));

            loginResponseDTO.setAcessToken(acessToken);
            loginResponseDTO.setPrefixToken(this.TOKEN_PREFIX);
            loginResponseDTO.setExpires(dateExpires);

            return ResponseEntity.ok().body(loginResponseDTO);

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new BadCredentialsException("Bad Credentials");
        }

    }
}
