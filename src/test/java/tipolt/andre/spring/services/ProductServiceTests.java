package tipolt.andre.spring.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.repositories.ProductRepository;

@DisplayName("ProductServiceTests")
public class ProductServiceTests extends ApplicationTestConfig{
    
    @Autowired
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private String existingId;
    private String notExistingId;

    

    @BeforeEach
    public void setUp() throws Exception{
        this.existingId = "1";
        this.notExistingId = "0";
    }

    @Test
    public void findProductShouldReturnProductWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            ProductModel product = productService.findProductById(existingId);


            Assertions.assertNotNull(product);
        });

        // Mockito.verify(productRepository, times(1)).findById(existingId);
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

        product.setName("André");
        product.setPrice(1.0);
        product.setCategoryId(notExistingCategoryId);

        Assertions.assertThrows(ObjectNotFoundException.class, () -> {

            productService.saveProduct(product);
        });
    }
}
