package mindera.mindswap.personalproject.dto.doctor;

import jakarta.persistence.OneToMany;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.doctor.SpecialityType;
import mindera.mindswap.personalproject.model.user.Patient;

import java.util.List;

public class DoctorCreateDto {

    private String name;
    private int age;
    private String email;
    private String habitation;
    private SpecialityType speciality;
    private List<Appointment> appointments;

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

    public String getHabitation() {
        return habitation;
    }

    public void setHabitation(String habitation) {
        this.habitation = habitation;
    }

    public SpecialityType getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityType speciality) {
        this.speciality = speciality;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
