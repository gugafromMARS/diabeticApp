package mindera.mindswap.personalproject.diabeticType.model;

public enum Type {

    ONE("one"),
    TWO("two");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
