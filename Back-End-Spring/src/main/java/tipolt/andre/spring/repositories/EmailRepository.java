package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, String>{
    
}
