package tipolt.andre.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;
import tipolt.andre.spring.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    List<ProductModel> findByCategoryId(String categoryId);

    List<ProductModel> findAllByNameStartsWithIgnoreCase(String nameProduct);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_product product WHERE product.name ilike %:nameProduct%")
    List<ProductModel> searchProduct(@Param("nameProduct") String nameProduct);
}
