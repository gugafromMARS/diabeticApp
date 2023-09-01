package mindera.mindswap.personalproject.dto.appointment;

import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.institution.Institution;
import mindera.mindswap.personalproject.model.patient.Patient;

import java.time.LocalDateTime;

public class AppointmentDto {

    private Long Id;
    private LocalDateTime localDateTime;
    private Patient patient;
    private Doctor doctor;
    private Institution institution;

    public AppointmentDto(Long id, LocalDateTime localDateTime, Patient patient, Doctor doctor, Institution institution) {
        Id = id;
        this.localDateTime = localDateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.institution = institution;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
