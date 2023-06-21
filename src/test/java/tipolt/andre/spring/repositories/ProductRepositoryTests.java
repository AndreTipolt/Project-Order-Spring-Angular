package tipolt.andre.spring.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tipolt.andre.spring.models.ProductModel;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private String existingId;
    private String notExistingId;

    @BeforeEach
    void setUp() {
        this.existingId = "1";
        this.notExistingId = "0";

    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() {

        Optional<ProductModel> product = productRepository.findById(existingId);

        Assertions.assertFalse(product.isEmpty());
    }

    @Test
    public void findByIdShouldReturnEmptyWhenIdDoesNotExists() {
        
        Optional<ProductModel> product = productRepository.findById(notExistingId);

        Assertions.assertTrue(product.isEmpty());
    }

    
}
