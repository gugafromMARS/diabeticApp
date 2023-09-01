package mindera.mindswap.personalproject.dto.patient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.register.Register;

import java.util.List;

public class PatientCreateDto {

    private String name;
    private String email;
    private int age;
    private double height;
    private int weight;
    private DiabeticDetails diabeticDetails;

    public PatientCreateDto() {
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

}
