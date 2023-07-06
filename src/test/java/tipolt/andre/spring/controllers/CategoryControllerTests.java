package tipolt.andre.spring.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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

    @BeforeEach
    public void setUp() {
        this.existingId = "1";
        this.nonExistingId = "0";
    }

    @Test
    @DisplayName("Find All should return list of categories")
    public void findAllShouldReturnListOfCategories() throws Exception {

        ResultActions result = mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Find Category by id should return not found when category id does not exists")
    public void findCategoryByIdShouldReturnNotFoundWhenCategoryIdDoesNotExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/categories/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Find Category By Id should return not found when category id exists")
    public void findCategoryIdShouldReturnNotFoundWhenCategoryIdExists() throws Exception {

        ResultActions result = mockMvc.perform(get("/categories/{id}", existingId)
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }
}
