package mindera.mindswap.personalproject.institution.model;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.appointment.model.Appointment;

import java.util.List;

@Entity(name ="institution")
@Table(name = "institution")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String email;
    private InstitutionType type;
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static InstitutionBuilder builder(){
        return new InstitutionBuilder();
    }

    public static class InstitutionBuilder {
        private Institution institution;

        public InstitutionBuilder() {
            institution = new Institution();
        }

        public InstitutionBuilder withName(String name){
            institution.setName(name);
            return this;
        }

        public InstitutionBuilder withAddress(String address){
            institution.setAddress(address);
            return this;
        }
        public InstitutionBuilder withEmail(String email){
            institution.setEmail(email);
            return this;
        }

        public InstitutionBuilder withType(InstitutionType type){
            institution.setType(type);
            return this;
        }

        public Institution build(){
            return institution;
        }
    }
}
