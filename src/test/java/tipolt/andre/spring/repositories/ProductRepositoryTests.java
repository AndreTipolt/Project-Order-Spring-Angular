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

    private Long existingId;
    private Long notExistingId;

    @BeforeEach
    void setUp() {
        this.existingId = 1L;
        this.notExistingId = 0L;

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
