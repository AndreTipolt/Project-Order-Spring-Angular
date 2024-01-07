package tipolt.andre.spring.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import org.springframework.stereotype.Service;

import tipolt.andre.spring.models.UserModel;


@Service
public class TokenService {
    
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserModel userModel){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                            .withIssuer("auth-api")
                            .withSubject(userModel.getEmail())
                            .withExpiresAt(genExpirarionDate())
                            .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            
            throw new RuntimeException("Error at create jwt token");
        }
    }

    private Instant genExpirarionDate(){
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
