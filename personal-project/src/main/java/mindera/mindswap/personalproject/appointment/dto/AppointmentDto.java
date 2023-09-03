package mindera.mindswap.personalproject.appointment.dto;

import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.institution.model.Institution;
import mindera.mindswap.personalproject.patient.model.Patient;

import java.time.LocalDate;

public class AppointmentDto {

    private Long Id;
    private LocalDate localDateTime;
    private Patient patient;
    private Doctor doctor;
    private Institution institution;

    public AppointmentDto(LocalDate localDateTime, Patient patient, Doctor doctor, Institution institution) {
        this.localDateTime = localDateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.institution = institution;
    }

    public AppointmentDto(LocalDate localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
