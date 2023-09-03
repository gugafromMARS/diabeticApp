package mindera.mindswap.personalproject.appointment.converter;


import mindera.mindswap.personalproject.appointment.dto.AppointmentCreateDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.model.Appointment;
import mindera.mindswap.personalproject.doctor.converter.DoctorConverter;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.patient.converter.PatientConverter;
import mindera.mindswap.personalproject.patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AppointmentConverter {


    private PatientConverter patientConverter;
    private DoctorConverter doctorConverter;

    @Autowired
    public AppointmentConverter(PatientConverter patientConverter, DoctorConverter doctorConverter) {
        this.patientConverter = patientConverter;
        this.doctorConverter = doctorConverter;
    }

    public AppointmentDto toDto(Appointment appointment){
        return new AppointmentDto(
                appointment.getId(),
                appointment.getLocalDate(),
                patientConverter.toDto(appointment.getPatient()),
                doctorConverter.toDto(appointment.getDoctor()),
                appointment.getDescription());
    }

    public Appointment fromCreateDto(AppointmentCreateDto appointmentCreateDto, Patient patient, Doctor doctor){
        return Appointment.builder()
                .localDate(appointmentCreateDto.getLocalDate())
                .patient(patient)
                .doctor(doctor)
                .description(appointmentCreateDto.getDescription())
                .build();
    }
}
