package mindera.mindswap.personalproject.model.user;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
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
    @OneToOne(cascade = CascadeType.ALL)
    private DiabeticDetails diabeticDetails;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Register> registerList;

    public User() {
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

        public UserBuilder withDiabeticDetails(DiabeticDetails diabeticDetails){
            user.setDiabeticDetails(diabeticDetails);
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
