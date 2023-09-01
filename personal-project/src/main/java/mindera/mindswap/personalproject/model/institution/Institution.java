package mindera.mindswap.personalproject.model.institution;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.patient.Patient;

import java.util.List;

@Entity(name ="institution")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private InstitutionType type;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InstitutionType getType() {
        return type;
    }

    public void setType(InstitutionType type) {
        this.type = type;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
