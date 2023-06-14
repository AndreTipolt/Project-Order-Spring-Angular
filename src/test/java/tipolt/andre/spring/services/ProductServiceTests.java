package tipolt.andre.spring.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
    
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private String existingId;
    private String notExistingId;

    

    @BeforeEach
    public void setUp() {
        this.existingId = "1";
        this.notExistingId = "0";
    }

    // @Test
    // public void findProductShouldReturnProductWhenIdExists() {

    //     ProductModel product = productService.findProductById(existingId);

    //     Assertions.assertNotNull(product);
    // }

    @Test
    public void findProductShouldThrowObjectNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {
            ProductModel product = productService.findProductById(notExistingId);
        });

    }
}
