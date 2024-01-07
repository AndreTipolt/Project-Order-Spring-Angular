package tipolt.andre.spring.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import tipolt.andre.spring.controllers.utils.ObjectMapperUtils;
import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @GetMapping
    public ResponseEntity<? extends Object> findAll() throws JsonMappingException, JsonProcessingException {

        JsonNode usersFindAllCached = objectMapperUtils.getRedisKeyAndConvertToJsonNode("users_findAll");

        if (usersFindAllCached != null) {
            return ResponseEntity.ok().body(usersFindAllCached);
        }

        List<UserModel> listUserModels = userService.findAll();
        objectMapperUtils.convertObjectToStringAndSaveInRedis("users_findAll", listUserModels);

        return ResponseEntity.ok().body(listUserModels);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserInsertDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.status(201).build();
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO,
            @PathVariable String userId) {
        userService.updateUser(userId, userUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}
