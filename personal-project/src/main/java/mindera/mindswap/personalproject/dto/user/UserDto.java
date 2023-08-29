package mindera.mindswap.personalproject.dto.user;

import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.model.type.DiabeticType;

import java.util.List;

public class UserDto {

    private Long id;
    private String name;

    private String email;
    private int age;
    private double InsulinPerCarbohydrate;
    private DiabeticType diabeticType;
    private List<Register> registerList;

    public UserDto(Long id, String name, String email, int age, double insulinPerCarbohydrate, DiabeticType diabeticType, List<Register> registerList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        InsulinPerCarbohydrate = insulinPerCarbohydrate;
        this.diabeticType = diabeticType;
        this.registerList = registerList;
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

    public double getInsulinPerCarbohydrate() {
        return InsulinPerCarbohydrate;
    }

    public void setInsulinPerCarbohydrate(double insulinPerCarbohydrate) {
        InsulinPerCarbohydrate = insulinPerCarbohydrate;
    }

    public DiabeticType getDiabeticType() {
        return diabeticType;
    }

    public void setDiabeticType(DiabeticType diabeticType) {
        this.diabeticType = diabeticType;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }
}
