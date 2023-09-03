package mindera.mindswap.personalproject.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.patient.dto.PatientDto;
import mindera.mindswap.personalproject.patient.model.Patient;

import java.time.LocalDate;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class AppointmentDto {

    private Long id;
    private LocalDate localDate;
    private PatientDto patientDto;
    private DoctorDto doctorDto;
    private String description;

    public AppointmentDto(Long id, LocalDate localDate, PatientDto patientDto, DoctorDto doctorDto, String description) {
        this.id = id;
        this.localDate = localDate;
        this.patientDto = patientDto;
        this.doctorDto = doctorDto;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public PatientDto getPatientDto() {
        return patientDto;
    }

    public void setPatientDto(PatientDto patientDto) {
        this.patientDto = patientDto;
    }

    public DoctorDto getDoctorDto() {
        return doctorDto;
    }

    public void setDoctorDto(DoctorDto doctorDto) {
        this.doctorDto = doctorDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
