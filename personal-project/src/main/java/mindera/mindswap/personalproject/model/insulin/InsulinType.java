package mindera.mindswap.personalproject.model.insulin;

public enum InsulinType {

    RAPID("rapid-acting"),
    SHORT("short-acting"),
    INTERMEDIATE("intermediate-acting"),
    MIXED("mixed"),
    LONG("long-acting");

    private final String type;

    InsulinType(String type) {
        this.type = type;
    }
}
