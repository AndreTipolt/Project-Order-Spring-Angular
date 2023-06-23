package tipolt.andre.spring.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import tipolt.andre.spring.ApplicationTestConfig;
import tipolt.andre.spring.dtos.UserInsertDTO;
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

    @Test
    @DisplayName("Find All should return list of users")
    public void findAllShouldReturnListOfUsers() throws Exception {

        ResultActions result = mockMvc.perform(get("/users")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Save User should return unprocessable entity when someone field is missing")
    public void saveUserShouldReturnUnprocessableEntityWhenSomeoneFieldIsMissing() throws Exception {

        UserInsertDTO userWithoutEmailField = UserFactory.createUserInsertDTOWithoutFieldEmail();

        String jsonBody = objectMapper.writeValueAsString(userWithoutEmailField);

        ResultActions result = mockMvc.perform(post("/users")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Save User Should return bad request when passwords not coincides")
    public void saveUserShouldReturnBadRequestWhenPasswordsNotCoincides() throws Exception {

        UserInsertDTO userWithDiffPasswords = UserFactory.createUserInsertDTOWithDiffPasswords();

        String jsonBody = objectMapper.writeValueAsString(userWithDiffPasswords);

        ResultActions result = mockMvc.perform(post("/users")
                .content(jsonBody)
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

        ResultActions result = mockMvc.perform(post("/users")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("save user should return unprocessable entity when email already exists")
    public void saveUserShouldReturnUnprocessableEntityWhenEmailAlreadyExists() throws Exception {

        UserInsertDTO userWithEmailFieldAlreadyExists = UserFactory.createUserInserDTOWithEmailFieldThatAlreadyExists();

        String jsonBody = objectMapper.writeValueAsString(userWithEmailFieldAlreadyExists);

        ResultActions result = mockMvc.perform(post("/users")
                .content(jsonBody)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnprocessableEntity());
    }
}
