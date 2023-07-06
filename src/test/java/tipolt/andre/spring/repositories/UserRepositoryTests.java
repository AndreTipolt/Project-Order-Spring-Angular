package tipolt.andre.spring.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tipolt.andre.spring.models.UserModel;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private String existingId;
    private String notExistingId;

    @BeforeEach
    public void setUp() {
        this.existingId = "1";
        this.notExistingId = "0";
    }

    @Test
    @DisplayName("Find Email Should Return Optional User When Email Exists")
    public void findEmailShouldReturnOptionalUserWhenEmailExists() {

        String existingEmail = "andretipoltlopes@gmail.com";
        Optional<UserModel> user = userRepository.findByEmail(existingEmail);

        Assertions.assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("Find By Email Should Return Empty Optional When Email Does Not Exists")
    public void findbyEmailShouldReturnEmptyOptionalWhenEmailDoesNotExists() {

        String notExistingEmail = "emailnotexists@notexists.com";

        Optional<UserModel> user = userRepository.findByEmail(notExistingEmail);

        Assertions.assertTrue(user.isEmpty());

    }

    @Test
    @DisplayName("Find by id should return optional user when id exists")
    public void findByIdShouldReturnsOptionalUserWhenIdExists() {

        Optional<UserModel> user = userRepository.findById(existingId);

        Assertions.assertTrue(user.isPresent());
    }

    @Test
    @DisplayName("Find by id should return empty optional user when id does not exists")
    public void findByIdShouldReturnEmptyOptionalWhenIdDoesNotExists() {

        Optional<UserModel> user = userRepository.findById(notExistingId);

        Assertions.assertTrue(user.isEmpty());

    }
}