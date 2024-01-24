package tipolt.andre.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tipolt.andre.spring.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    List<ProductModel> findByCategoryId(String categoryId);

    List<ProductModel> findAllByNameStartsWithIgnoreCase(String nameProduct);
}
