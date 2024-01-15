package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.AdressModel;

@Repository
public interface AdressRepository extends JpaRepository<AdressModel, String>{
    
}
