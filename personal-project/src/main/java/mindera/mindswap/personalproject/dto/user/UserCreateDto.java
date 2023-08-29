package mindera.mindswap.personalproject.dto.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.insulin.Insulin;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.model.type.DiabeticType;

import java.util.List;

public class UserCreateDto {

    private String name;
    private String email;
    private int age;
    private double height;
    private int weight;
    @OneToOne
    private DiabeticDetails diabeticDetails;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Register> registerList;

    public UserCreateDto() {
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public DiabeticDetails getDiabeticDetails() {
        return diabeticDetails;
    }

    public void setDiabeticDetails(DiabeticDetails diabeticDetails) {
        this.diabeticDetails = diabeticDetails;
    }

    public List<Register> getRegisterList() {
        return registerList;
    }

    public void setRegisterList(List<Register> registerList) {
        this.registerList = registerList;
    }
}
