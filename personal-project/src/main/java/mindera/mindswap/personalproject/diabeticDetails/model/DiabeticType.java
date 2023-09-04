package mindera.mindswap.personalproject.diabeticDetails.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public enum DiabeticType {

    ONE("one"),
    TWO("two");

    private final String type;

    DiabeticType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
