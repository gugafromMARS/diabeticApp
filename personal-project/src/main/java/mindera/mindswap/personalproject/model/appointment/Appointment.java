package mindera.mindswap.personalproject.model.appointment;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.institution.Institution;
import mindera.mindswap.personalproject.model.patient.Patient;

import java.time.LocalDateTime;

@Entity(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Institution institution;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static AppointmentBuilder builder() {
        return new AppointmentBuilder();
    }

    public static class AppointmentBuilder {
        private Appointment appointment;

        public AppointmentBuilder() {
            appointment = new Appointment();
        }

        public AppointmentBuilder withLocalDate(LocalDateTime date){
            appointment.setLocalDateTime(date);
            return this;
        }

        public AppointmentBuilder withPatient (Patient patient){
            appointment.setPatient(patient);
            return this;
        }

        public AppointmentBuilder withDoctor(Doctor doctor){
            appointment.setDoctor(doctor);
            return this;
        }

        public AppointmentBuilder withInstitution(Institution institution){
            appointment.setInstitution(institution);
            return this;
        }

        public AppointmentBuilder withDescription(String description){
            appointment.setDescription(description);
            return this;
        }

        public Appointment build(){
            return appointment;
        }
    }
}
