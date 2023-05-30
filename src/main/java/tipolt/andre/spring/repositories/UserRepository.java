package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String>{
    
}
