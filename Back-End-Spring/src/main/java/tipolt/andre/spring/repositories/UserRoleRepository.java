package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.UserRoleModel;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleModel, String> {

}
