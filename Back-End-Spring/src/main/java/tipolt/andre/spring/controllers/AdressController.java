package tipolt.andre.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tipolt.andre.spring.dtos.AdressDTO;
import tipolt.andre.spring.services.AdressService;

@RestController
@RequestMapping(value = "/adresses")
public class AdressController {

    @Autowired
    private AdressService adressService;

    @PostMapping
    public ResponseEntity<Void> saveAdress(@RequestBody @Valid AdressDTO adressDTO) {

        adressService.saveAdress(adressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
