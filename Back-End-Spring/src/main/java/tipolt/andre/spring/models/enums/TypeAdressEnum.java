package tipolt.andre.spring.models.enums;

public enum TypeAdressEnum {
    
    WORK("Work"),
    HOME("Home");

    private String typeAdress;

    private TypeAdressEnum(String typeAdress) {
        this.typeAdress = typeAdress;
    }

    public String getTypeAdress() {
        return typeAdress;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
