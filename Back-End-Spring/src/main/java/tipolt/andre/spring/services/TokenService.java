package tipolt.andre.spring.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import tipolt.andre.spring.models.UserModel;

@Service
public class TokenService {

    @Value("${jwt.secret.acess}")
    private String secretAcess;

    @Value("${jwt.secret.forgotpassword}")
    private String secretForgotPassword;

    @Value("${jwt.duration}")
    private Long duration;

    public String generateToken(UserModel userModel, boolean forgotPassword) {

        Algorithm algorithm = Algorithm.HMAC256(secretAcess);
        try {

            if (forgotPassword) {
                algorithm = Algorithm.HMAC256(secretForgotPassword);
            } else {
                algorithm = Algorithm.HMAC256(secretAcess);
            }

            String token = JWT.create()
                    .withIssuer("order-spring")
                    .withSubject(userModel.getId().toString())
                    .withClaim("email", userModel.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {

            throw new RuntimeException("Error at create jwt token");
        }
    }

    public String validateToken(String acessToken, boolean forgotPassword) {

        Algorithm algorithm = null;

        try {

            if (forgotPassword) {
                algorithm = Algorithm.HMAC256(secretForgotPassword);
            } else {
                algorithm = Algorithm.HMAC256(secretAcess);
            }

            String userId = JWT.require(algorithm)
                    .withIssuer("order-spring")
                    .build()
                    .verify(acessToken)
                    .getSubject();

            return userId;

        } catch (JWTVerificationException exception) {

            return null;
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusSeconds(duration).toInstant(ZoneOffset.of("-03:00"));
    }

}
