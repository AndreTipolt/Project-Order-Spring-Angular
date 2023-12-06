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
import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.factories.UserFactory;
import tipolt.andre.spring.repositories.UserRepository;
import tipolt.andre.spring.services.UserService;

public class UserControllerTests extends ApplicationTestConfig {

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void setUp() throws Exception {
        this.existingId = "1";
        this.nonExistingId = "0";
        this.username = "andretipoltlopes@gmail.com";
        this.password = "andre1234";
        this.acessToken = obtainAccessToken(username, password);
    }

    @Test
    @DisplayName("Find All should return list of users")
    public void findAllShouldReturnListOfUsers() throws Exception {

        ResultActions result = mockMvc.perform(get("/users")
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Save User should return unprocessable entity when someone field is missing")
    public void saveUserShouldReturnUnprocessableEntityWhenSomeoneFieldIsMissing() throws Exception {

        UserInsertDTO userWithoutEmailField = UserFactory.createUserInsertDTOWithoutFieldEmail();

        String jsonBody = objectMapper.writeValueAsString(userWithoutEmailField);

        ResultActions result = mockMvc.perform(post("/users/save")
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Save User Should return bad request when passwords not coincides")
    public void saveUserShouldReturnBadRequestWhenPasswordsNotCoincides() throws Exception {

        UserInsertDTO userWithDiffPasswords = UserFactory.createUserInsertDTOWithDiffPasswords();

        String jsonBody = objectMapper.writeValueAsString(userWithDiffPasswords);

        ResultActions result = mockMvc.perform(post("/users/save")
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Save User should return created when content is valid")
    public void saveUserShouldReturnCreatedWhenContentIsValid() throws Exception {

        UserInsertDTO userDTO = UserFactory.createUserInsertDTOValid();

        userDTO.setEmail("validemail@email.com"); // It's for don't throw email exception

        String jsonBody = objectMapper.writeValueAsString(userDTO);

        ResultActions result = mockMvc.perform(post("/users/save")
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("save user should return unprocessable entity when email already exists")
    public void saveUserShouldReturnUnprocessableEntityWhenEmailAlreadyExists() throws Exception {

        UserInsertDTO userWithEmailFieldAlreadyExists = UserFactory
                .createUserInsertDTOWithEmailFieldThatAlreadyExists();

        String jsonBody = objectMapper.writeValueAsString(userWithEmailFieldAlreadyExists);

        ResultActions result = mockMvc.perform(post("/users/save")
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Update User should return not found when user id does not exists")
    public void updateUserShouldReturnObjectNotFoundExceptionWhenUserIdDoesNotExists() throws Exception {

        UserUpdateDTO userUpdateDTOValid = UserFactory.createUserUpdateDTOWhenIdUserDoesNotExist();

        String jsonBody = objectMapper.writeValueAsString(userUpdateDTOValid);

        ResultActions result = mockMvc.perform(put("/users/{id}", nonExistingId)
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update User should return bad request when passwords not coincides")
    public void updateUserShouldReturnPasswordNotCoincideExceptionWhenPasswordsNotCoincides() throws Exception {

        UserUpdateDTO userUpdateDTOWithDiffPasswords = UserFactory.createUserUpdateDTOWithDiffPasswords();

        String jsonBody = objectMapper.writeValueAsString(userUpdateDTOWithDiffPasswords);

        ResultActions result = mockMvc.perform(put("/users/{id}", existingId)
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update User Should return unprocessable entity when user id try change email that not belongs himself")
    public void updateUserShouldReturnUnprocessableEntityWhenUserIdTruyChangeEmailThatNotBelongsHimself()
            throws Exception {

        UserUpdateDTO userUpdateDTOWithEmailNotBelongsUser = UserFactory.createUserUpdateWithEmailNotBelongsUserId();

        String jsonBody = objectMapper.writeValueAsString(userUpdateDTOWithEmailNotBelongsUser);

        ResultActions result = mockMvc.perform(put("/users/{id}", existingId)
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("update user should return no content when UserUpdateDTO is valid")
    public void updateUserShouldReturnNoContentWhenUserUpdateDTOIsValid() throws Exception {
        UserUpdateDTO userUpdateDTOWithEmailNotBelongsUser = UserFactory.createUserUpdateDTOValid();

        userUpdateDTOWithEmailNotBelongsUser.setEmail("emailneverused@gmail.com"); // It's for doesn't throw email
                                                                                   // already exists
        String jsonBody = objectMapper.writeValueAsString(userUpdateDTOWithEmailNotBelongsUser);

        ResultActions result = mockMvc.perform(put("/users/{id}", "3")
                .content(jsonBody)
                .header("Authorization", "Bearer " + acessToken)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
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
