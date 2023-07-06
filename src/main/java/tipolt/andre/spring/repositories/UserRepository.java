package tipolt.andre.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
}
