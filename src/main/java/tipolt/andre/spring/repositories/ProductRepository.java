package tipolt.andre.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String>{
    
}
