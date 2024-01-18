package tipolt.andre.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.NonNull;
import tipolt.andre.spring.dtos.AdressDTO;
import tipolt.andre.spring.dtos.mappers.AdressMapper;
import tipolt.andre.spring.exceptions.AcessDeniedException;
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
    public List<AdressModel> findAllAdressByUser() {

        UserModel user = authService.getUserInAuthentication();

        return adressRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ObjectNotFoundException("User Id not found"));
    }

    @Transactional(readOnly = true)
    public void deleteAdress(String adressId) {

        if (adressId != null) {

            AdressModel adressModel = this.findAdressById(adressId);

            if (this.canDeleteAdress(adressModel)) { // Checks if the user that wants to delete the adress is yours or
                                                     // he is a admin

                adressRepository.deleteById(adressId);

                return;
            }
        }

        throw new AcessDeniedException("You don't have acess to use this resource");
    }

    private AdressModel findAdressById(String adressId) {
        return this.adressRepository.findById(adressId)
                .orElseThrow(() -> new ObjectNotFoundException("Adress Not Found"));
    }

    private boolean canDeleteAdress(AdressModel adressModel) {

        UserModel user = authService.getUserInAuthentication();

        if (adressModel.getUser().getId().equals(user.getId()) | user.hasRole("ROLE_ADMIN")) {

            return true;
        }
        return false;
    }
}
