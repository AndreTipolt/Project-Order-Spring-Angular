package tipolt.andre.spring.models.enums;

public enum StatusOrder {

    WAIT_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    private StatusOrder(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static StatusOrder valueOf(int code) {
        for (StatusOrder value : StatusOrder.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Code");
    }
}
