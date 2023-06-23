package tipolt.andre.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tipolt.andre.spring.dtos.UserInsertDTO;
import tipolt.andre.spring.dtos.UserUpdateDTO;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserInsertDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO,
                                           @PathVariable String userId) {
        userService.updateUser(userId, userUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}
