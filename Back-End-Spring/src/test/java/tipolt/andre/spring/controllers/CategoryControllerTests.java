package tipolt.andre.spring.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.repositories.CategoryRepository;
import tipolt.andre.spring.services.CategoryService;

public class CategoryControllerTests extends ApplicationTestConfig {

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;

    private String existingId;
    private String nonExistingId;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    private String username;
    private String password;

    private String acessToken;

    @BeforeEach
    public void setUp() throws Exception {
        this.existingId = "1";
        this.nonExistingId = "0";
        this.username = "andretipoltlopes@gmail.com";
        this.password = "andre1234";
        this.acessToken = obtainAccessToken(username, password);
    }

    @Test
    @DisplayName("Find All should return list of categories")
    public void findAllShouldReturnListOfCategories() throws Exception {

        ResultActions result = mockMvc.perform(get("/categories")
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Find Category by id should return not found when category id does not exists")
    public void findCategoryByIdShouldReturnNotFoundWhenCategoryIdDoesNotExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/categories/{id}", nonExistingId)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find Category By Id should return not found when category id exists")
    public void findCategoryIdShouldReturnNotFoundWhenCategoryIdExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/categories/{id}", existingId)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
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