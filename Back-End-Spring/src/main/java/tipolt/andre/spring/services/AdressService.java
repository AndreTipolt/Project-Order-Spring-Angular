package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tipolt.andre.spring.dtos.AdressDTO;
import tipolt.andre.spring.dtos.mappers.AdressMapper;
import tipolt.andre.spring.exceptions.ObjectNotFoundException;
import tipolt.andre.spring.models.AdressModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.repositories.AdressRepository;

@Service
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private AdressMapper adressMapper;

    @Autowired
    private AuthService authService;

    @Transactional
    public void saveAdress(AdressDTO adressDTO) {

        AdressModel adress = adressMapper.convertDTOtoAdressModel(adressDTO);

        adressRepository.save(adress);
    }

    @Transactional(readOnly = true)
    public List<AdressModel> findAllAdressByUser(){

        UserModel user = authService.getUserInAuthentication();

        return adressRepository.findByUserId(user.getId()).orElseThrow(() -> new ObjectNotFoundException("User Id not found"));
    }
}
