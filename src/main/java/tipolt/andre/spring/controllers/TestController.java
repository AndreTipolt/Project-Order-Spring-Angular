package tipolt.andre.spring.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tipolt.andre.spring.models.ProductModel;
import tipolt.andre.spring.services.ProductService;
import tipolt.andre.spring.services.RedisCacheService;

@RestController
@RequestMapping("/test")
public class TestController implements Serializable{

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public void saveJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(productService.findAll());
        redisCacheService.saveJson("product", json);
    }

    @GetMapping("/get")
    public List<ProductModel> getJson() throws JsonMappingException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        
        String response = redisCacheService.getJson("product");

        List<ProductModel> jsonNode = objectMapper.readValue(response, new TypeReference<List<ProductModel>>() {});

        return jsonNode;
    }
}