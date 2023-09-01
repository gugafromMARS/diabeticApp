package mindera.mindswap.personalproject.dto.doctor;

import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.doctor.SpecialityType;

import java.util.List;

public class DoctorCreateDto {

    private String name;
    private int age;
    private String email;
    private String address;
    private SpecialityType speciality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SpecialityType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityType speciality) {
        this.speciality = speciality;
    }

}
