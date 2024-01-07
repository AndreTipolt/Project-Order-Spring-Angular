package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tipolt.andre.spring.dtos.AuthenticationDTO;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public void login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                authenticationDTO.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

    }
}
