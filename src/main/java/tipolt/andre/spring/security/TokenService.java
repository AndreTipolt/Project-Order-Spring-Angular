package tipolt.andre.spring.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import tipolt.andre.spring.models.UserModel;

@Service
public class TokenService {

    @Value("${secret.jwt}")
    private String SecretJWT;
    
    public String generateToken(UserModel user){

        return JWT.create().withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256(SecretJWT));
    }
}
