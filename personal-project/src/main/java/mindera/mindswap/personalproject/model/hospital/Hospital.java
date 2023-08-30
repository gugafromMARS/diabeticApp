package mindera.mindswap.personalproject.model.hospital;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.user.Patient;

import java.util.List;

@Entity(name ="hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Patient> patients;
    @OneToMany
    private List<Doctor> doctors;

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

    public List<Patient> getUsers() {
        return patients;
    }

    public void setUsers(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
