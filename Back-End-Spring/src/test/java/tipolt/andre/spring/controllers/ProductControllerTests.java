package tipolt.andre.spring.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.ProductDTO;
import tipolt.andre.spring.factories.ProductFactory;
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

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    private String username;
    private String password;

    private String acessToken;

    @BeforeEach
    public void setUp() throws Exception {

        // this.productDTO = ProductFactory.createProductDTO();

        this.existingId = "1";
        this.nonExistingId = "0";

        this.username = "andretipoltlopes@gmail.com";
        this.password = "andre1234";
        this.acessToken = obtainAccessToken(username, password);
    }

    @Test
    @DisplayName("Find by id should return product when id exists")
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/{id}", existingId)
                    .header("Authorization", "Bearer " + acessToken)
                    .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Find by id should return not found when id does not exists")
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/products/{id}", nonExistingId)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find all should rerun page")
    public void findAllShouldReturnPage() throws Exception {
        ResultActions result = mockMvc.perform(get("/products")
        .header("Authorization", "Bearer " + acessToken)
        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Update Product should return no content when id product exists")
    public void updateProductShouldReturnNoContentWhenIdProductExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", existingId)
                .header("Authorization", "Bearer " + acessToken)
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
                .header("Authorization", "Bearer " + acessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update Product should return not found when id category does not exists")
    public void updateProductShouldReturnNotFoundWhenIdCategoryDoesNotExists() throws Exception {

        Long nonExistingCategoryId = 0L;

        // ProductDTO productDTO = new ProductDTO("Phone", 1999.99, nonExistingCategoryId);

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(put("/products/{id}", existingId)
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
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
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("save product should return created when all fields are filled")
    public void saveProductShouldReturnCreatedWhenAllFieldsAreeFilled() throws Exception {
        // ProductDTO productDTO = ProductFactory.createProductDTO();

        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result = mockMvc.perform(post("/products")
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    private String obtainAccessToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);

		ResultActions result = mockMvc
				.perform(post("/oauth/token").params(params).with(httpBasic(clientId, clientSecret))
						.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}	

}
