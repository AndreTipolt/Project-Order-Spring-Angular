package tipolt.andre.spring.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tipolt.andre.spring.services.RedisCacheService;

@Service
public class ObjectMapperUtils {

    @Autowired
    private  RedisCacheService redisCacheService;

    private  ObjectMapper objectMapper = new ObjectMapper();

    public  void convertObjectToStringAndSaveInRedis(String key, Object object) {

        
        try {
            
            String json = objectMapper.writeValueAsString(object);

            redisCacheService.save(key, json);

        } catch (JsonProcessingException e) {

            throw new RuntimeException("Error to save data in redis");

        }
        
    }

    public JsonNode getRedisKeyAndConvertToJsonNode(String key) throws JsonMappingException, JsonProcessingException {

        String contentKey = redisCacheService.get(key);

        if(contentKey != null){

            JsonNode contentKeyInTypeSpecified = objectMapper.readTree(contentKey);
            return contentKeyInTypeSpecified;
        }

        return null;
    }

}
