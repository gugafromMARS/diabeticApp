package mindera.mindswap.personalproject.dto.user;

import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.model.type.DiabeticType;

import java.util.List;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private int age;
    private DiabeticDetails diabeticDetails;

    public UserDto(Long id, String name, String email, int age, DiabeticDetails diabeticDetails) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.diabeticDetails = diabeticDetails;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DiabeticDetails getDiabeticDetails() {
        return diabeticDetails;
    }

    public void setDiabeticDetails(DiabeticDetails diabeticDetails) {
        this.diabeticDetails = diabeticDetails;
    }
}
