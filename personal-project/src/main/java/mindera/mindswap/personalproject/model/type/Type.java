package mindera.mindswap.personalproject.model.type;

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
