package mindera.mindswap.personalproject.model.patient;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.register.Register;

import java.util.List;

@Entity(name = "patient")
public class Patient {

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public Patient() {
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }
    public static class UserBuilder {

        private Patient patient;

        public UserBuilder() {
            patient = new Patient();
        }

        public UserBuilder withName(String name){
            patient.setName(name);
            return this;
        }

        public UserBuilder withEmail(String email){
            patient.setEmail(email);
            return this;
        }
        public UserBuilder withAge(int age){
            patient.setAge(age);
            return this;
        }
        public UserBuilder withHeight(double height){
            patient.setHeight(height);
            return this;
        }

        public UserBuilder withWeight(int weight){
            patient.setWeight(weight);
            return this;
        }

        public UserBuilder withDiabeticDetails(DiabeticDetails diabeticDetails){
            patient.setDiabeticDetails(diabeticDetails);
            return this;
        }

        public UserBuilder withRegisters(List<Register> registers){
            patient.setRegisterList(registers);
            return this;
        }

        public UserBuilder withAppointments(List<Appointment> appointments){
            patient.setAppointments(appointments);
            return this;
        }

        public Patient build(){
            return patient;
        }
    }
}
