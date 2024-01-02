package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

}
