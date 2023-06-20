package tipolt.andre.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tipolt.andre.spring.dtos.UserDTO;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserModel> findAll(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserDTO userDTO){
        userService.saveUser(userDTO);
        return ResponseEntity.status(201).body(null);
    }
}
