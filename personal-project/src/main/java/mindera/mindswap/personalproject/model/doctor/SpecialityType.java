package mindera.mindswap.personalproject.model.doctor;

public enum SpecialityType {


    CARDIOLOGIST("cardiologist"),
    ENDOCRINOLOGIST("endocrinologist"),
    DERMATOLOGIST("dermatologist"),
    NEUROLOGIST("neurologist"),
    OPHTHALMOLOGIST("ophthalmologist"),
    ONCOLOGIST("oncologist");

    private final String type;

    SpecialityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
