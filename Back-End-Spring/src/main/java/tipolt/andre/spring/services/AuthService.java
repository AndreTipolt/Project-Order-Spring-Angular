package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.UserRepository;
import tipolt.andre.spring.services.exceptions.ForbiddenException;
import tipolt.andre.spring.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

    @Autowired
    public UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserModel authenticated() {
        try {

            String email = SecurityContextHolder.getContext().getAuthentication().getName();

            UserModel user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UnauthorizedUserException("Invalid User"));
            return user;

        } catch (Exception e) {
            throw new UnauthorizedException("Invalid User");
        }
    }

    @Transactional(readOnly = true)
    public void validateSelfOrAdmin(Long userId) {

        UserModel user = authenticated();

        if (!user.hasRole("ADMIN") && !user.getId().equals(userId)) {
            throw new ForbiddenException("Acess Denied");
        }
    }
}
