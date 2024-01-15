package tipolt.andre.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tipolt.andre.spring.repositories.AdressRepository;

@Service
public class AdressService {
    
    @Autowired
    private AdressRepository adressRepository;

    public void saveAdress(){
        
    }
}
