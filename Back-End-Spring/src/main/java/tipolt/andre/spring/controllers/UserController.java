package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.validation.Valid;
import tipolt.andre.spring.controllers.utils.ObjectMapperUtils;
import tipolt.andre.spring.dtos.ChangePasswordDTO;
import tipolt.andre.spring.dtos.DataHeaderDTO;
import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.AuthService;
import tipolt.andre.spring.services.TokenService;
import tipolt.andre.spring.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<? extends Object> findDataUser() throws JsonMappingException, JsonProcessingException {

        // JsonNode usersFindAllCached =
        // objectMapperUtils.getRedisKeyAndConvertToJsonNode("users_findAll");

        // if (usersFindAllCached != null) {
        // return ResponseEntity.ok().body(usersFindAllCached);
        // }

        UserModel userInAuthentication = authService.getUserInAuthentication();

        UserModel userData = userService.findDataUser(userInAuthentication);
        // objectMapperUtils.convertObjectToStringAndSaveInRedis("users_findAll",
        // userData);

        return ResponseEntity.ok().body(userData);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserInsertDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PutMapping(value = "/{userId}")
    // public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO,
    //         @PathVariable String userId) {
    //     userService.updateUser(userId, userUpdateDTO);
    //     return ResponseEntity.noContent().build();
    // }

    @GetMapping(value = "/header")
    public ResponseEntity<DataHeaderDTO> getDataHeader() {

        DataHeaderDTO dataHeaderDTO = this.userService.getDataHeader();

        return ResponseEntity.ok().body(dataHeaderDTO);
    }

    @PutMapping(value = "/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO, @RequestParam String token) {

        String userId = this.tokenService.validateToken(token, true);

        System.out.println();

        this.userService.changePassword(changePasswordDTO, userId);

        return ResponseEntity.noContent().build();
    }

}
