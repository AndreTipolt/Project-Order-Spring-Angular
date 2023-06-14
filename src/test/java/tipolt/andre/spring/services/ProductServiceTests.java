package tipolt.andre.spring.services;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.util.matcher.RequestMatchers;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.ProductRepository;

@SpringBootTest
@Transactional
public class ProductServiceTests {
    
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private String existingId;
    private String notExistingId;

    

    @BeforeEach
    public void setUp() {
        this.existingId = "1";
        this.notExistingId = "0";
    }

    @Test
    public void findProductShouldReturnProductWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            ProductModel product = productService.findProductById(existingId);

            Assertions.assertNotNull(product);
        });

        // Mockito.verify(productService).findProductById(existingId);
    }

    @Test
    public void findProductShouldThrowObjectNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            productService.findProductById(notExistingId);
        });
        
        // Mockito.verify(productRepository).findById(notExistingId);
    }

    @Test
    public void saveProductShouldReturnObjectNotFoundExceptionWhenCategoryIdDoesNotExist() {
        
        String notExistingCategoryId = "0";

        ProductDTO product = new ProductDTO();

        product.setName(ArgumentMatchers.anyString());
        product.setPrice(ArgumentMatchers.anyDouble());
        product.setCategoryId(notExistingCategoryId);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            productService.saveProduct(product);
        });
    }
}
