package mindera.mindswap.personalproject.appointment.model;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.institution.model.Institution;
import mindera.mindswap.personalproject.patient.model.Patient;

import java.time.LocalDate;

@Entity(name = "appointment")
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate localDateTime;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDate localDateTime) {
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

        public AppointmentBuilder withLocalDate(LocalDate date){
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
