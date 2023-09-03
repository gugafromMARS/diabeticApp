package mindera.mindswap.personalproject.institution.model;

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
