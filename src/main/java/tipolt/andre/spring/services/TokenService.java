package tipolt.andre.spring.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;

import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.exceptions.InvalidJWTException;

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

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SecretJWT)).build().verify(token).getSubject();
        } catch (JWTDecodeException e) {
            throw new InvalidJWTException("Token Invalido");
        }
        catch (SignatureVerificationException a){
            throw new InvalidJWTException("Token Invalido");
        }
    }
}
