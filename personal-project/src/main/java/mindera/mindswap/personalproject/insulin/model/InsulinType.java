package mindera.mindswap.personalproject.insulin.model;

public enum InsulinType {

    RAPID("rapid-acting"),
    SHORT("short-acting"),
    INTERMEDIATE("intermediate-acting"),
    MIXED("mixed"),
    LONG("long-acting");

    private final String insulinType;

    InsulinType(String insulinType) {
        this.insulinType = insulinType;
    }
}
