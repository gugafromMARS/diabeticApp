package mindera.mindswap.personalproject.model.hospital;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.model.user.User;

import java.util.List;

@Entity(name ="hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<User> users;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
