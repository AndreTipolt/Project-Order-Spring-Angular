package tipolt.andre.spring.dtos.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tipolt.andre.spring.dtos.AdressDTO;
import tipolt.andre.spring.models.AdressModel;
import tipolt.andre.spring.models.UserModel;
import tipolt.andre.spring.models.enums.TypeAdressEnum;
import tipolt.andre.spring.services.UserService;

@Component
public class AdressMapper {
    

    @Autowired
    private UserService userService;


    public AdressModel convertDTOtoAdressModel(AdressDTO adressDTO){

        UserModel user = userService.findUserById(adressDTO.getUserId());

        AdressModel adressModel = new AdressModel();

        adressModel.setCep(adressDTO.getCep());
        adressModel.setCity(adressDTO.getCity());
        adressModel.setComplement(adressDTO.getComplement());
        adressModel.setNeighborhood(adressDTO.getNeighborhood());
        adressModel.setNumber(adressDTO.getNumber());
        adressModel.setState(adressDTO.getState());
        adressModel.setUser(user);
        adressModel.setTypeAdress(convertToTypeAdress(adressDTO.getTypeAdress()));

        return adressModel;

    }

    private TypeAdressEnum convertToTypeAdress(String typeAdress){

        if(typeAdress == null){
            return null;
        }

        return switch(typeAdress){
            case "WORK" -> TypeAdressEnum.WORK;
            case "HOME" -> TypeAdressEnum.HOME;
            default -> throw new IllegalArgumentException("Illegal typeAdress");
        };
    }
}
