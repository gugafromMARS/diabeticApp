package mindera.mindswap.personalproject.model.institution;

public enum InstitutionType {


    HOSPITAL("hospital"),
    CLINIC("clinic"),
    HEALTHCENTER("health center");

    public final String type;

    InstitutionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
