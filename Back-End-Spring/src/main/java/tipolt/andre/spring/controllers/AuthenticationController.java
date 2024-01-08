package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.getEmail(),
                authenticationDTO.getPassword());

        try {

            Authentication auth = this.authenticationManager.authenticate(usernamePassword); // Do the authentication
            String acessToken = tokenService.generateToken((UserModel) auth.getPrincipal());

            return ResponseEntity.ok().body(new LoginResponseDTO(acessToken));

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new BadCredentialsException("Bad Credentials");
        }

    }
}
