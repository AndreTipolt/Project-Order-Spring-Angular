package tipolt.andre.spring.models.enums.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tipolt.andre.spring.models.enums.TypeAdressEnum;

@Converter(autoApply = true)
public class TypeAdressEnumConverter implements AttributeConverter<TypeAdressEnum, String> {

    @Override
    public String convertToDatabaseColumn(TypeAdressEnum typeAdress) {

        if (typeAdress == null) {
            return null;
        }
        return typeAdress.getTypeAdress();
    }

    @Override
    public TypeAdressEnum convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }
        for (TypeAdressEnum typeAdress : TypeAdressEnum.values()) {

            if (typeAdress.getTypeAdress().equals(dbData)) {

                return typeAdress;
            }
        }

        throw new IllegalArgumentException("Illegal type adress");
    }

}
