package mindera.mindswap.personalproject.dto.appointment;

import jakarta.persistence.ManyToOne;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.institution.Institution;
import mindera.mindswap.personalproject.model.patient.Patient;

import java.time.LocalDateTime;

public class AppointmentCreateDto {

    private LocalDateTime localDateTime;
    private Patient patient;
    private Doctor doctor;
    private Institution institution;
    private String description;

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
}
