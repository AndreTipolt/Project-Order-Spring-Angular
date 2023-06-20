package tipolt.andre.spring.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.controllers.factories.ProductFactory;
import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.repositories.ProductRepository;
import tipolt.andre.spring.services.ProductService;

public class ProductControllerTests extends ApplicationTestConfig { // Set permit all in WebConfigure

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String existingId;
    private String nonExistingId;

    private ProductDTO productDTO;

    @BeforeEach
    public void setUp() {

        this.productDTO = ProductFactory.createProductDTO();

        this.existingId = "1";
        this.nonExistingId = "0";
    }

    @Test
    @DisplayName("Find by id should return product when id exists")
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/{id}", existingId).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Find by id should return not found when id does not exists")
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/{id}", nonExistingId)
                                                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find all should rerun page")
    public void findAllShouldReturnPage() throws Exception {
        ResultActions result = mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update Product should return no content when id product exists")
    public void updateProductShouldReturnNoContentWhenIdProductExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", existingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Update Product should return not found when id product does not exists")
    public void updateProductShouldReturnNotFoundWhenIdProductDoesNotExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", nonExistingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update Product should return not found when id category does not exists")
    public void updateProductShouldReturnNotFoundWhenIdCategoryDoesNotExists() throws Exception {

        String nonExistingCategoryId = "0";

        ProductDTO productDTO = new ProductDTO("Phone", 1999.99, nonExistingCategoryId);

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", existingId)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Save Product should return bad request when someone field is missing")
    public void saveProductShouldReturnBadRequestWhenSomeoneFieldIsMissing() throws Exception {

        ProductDTO productMissingField = ProductFactory.createProductDTOMissingField();

        String jsonBody = objectMapper.writeValueAsString(productMissingField);

        ResultActions result = mockMvc.perform(post("/products")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("save product should return created when all fields are filled")
    public void saveProductShouldReturnCreatedWhenAllFieldsAreeFilled() throws Exception {
        ProductDTO productDTO = ProductFactory.createProductDTO();
    
        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(post("/products")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isCreated());
    }

}
