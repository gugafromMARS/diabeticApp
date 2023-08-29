package mindera.mindswap.personalproject.model.user;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.insulin.Insulin;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.model.type.DiabeticType;

import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private int age;
    private double height;
    private int weight;
    private double InsulinPerCarbohydrate;
    @OneToMany
    private List<Insulin> insulinList;
    @OneToOne
    private DiabeticType diabeticType;
    @OneToMany
    private List<Register> registerList;

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

    public double getInsulinPerCarbohydrate() {
        return InsulinPerCarbohydrate;
    }

    public void setInsulinPerCarbohydrate(double insulinPerCarbohydrate) {
        InsulinPerCarbohydrate = insulinPerCarbohydrate;
    }

    public List<Insulin> getInsulinList() {
        return insulinList;
    }

    public void setInsulinList(List<Insulin> insulinList) {
        this.insulinList = insulinList;
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

    public static UserBuilder builder(){
        return new UserBuilder();
    }
    public static class UserBuilder {

        private User user;

        public UserBuilder() {
            user = new User();
        }

        public UserBuilder withName(String name){
            user.setName(name);
            return this;
        }

        public UserBuilder withEmail(String email){
            user.setEmail(email);
            return this;
        }
        public UserBuilder withAge(int age){
            user.setAge(age);
            return this;
        }
        public UserBuilder withHeight(double height){
            user.setHeight(height);
            return this;
        }

        public UserBuilder withWeight(int weight){
            user.setWeight(weight);
            return this;
        }

        public UserBuilder withInsulinPerCarbo(double insulin){
            user.setInsulinPerCarbohydrate(insulin);
            return this;
        }

        public UserBuilder withInsulin(List<Insulin> insulinList){
            user.setInsulinList(insulinList);
            return this;
        }

        public UserBuilder withDiabeticType(DiabeticType diabeticType){
            user.setDiabeticType(diabeticType);
            return this;
        }

        public UserBuilder withRegisters(List<Register> registers){
            user.setRegisterList(registers);
            return this;
        }

        public User build(){
            return user;
        }
    }
}
