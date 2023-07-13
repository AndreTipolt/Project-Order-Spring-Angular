package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

}
