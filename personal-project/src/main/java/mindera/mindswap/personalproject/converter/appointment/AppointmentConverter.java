package mindera.mindswap.personalproject.converter.appointment;


import mindera.mindswap.personalproject.dto.appointment.AppointmentCreateDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.converter.doctor.DoctorConverter;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.converter.patient.PatientConverter;
import mindera.mindswap.personalproject.model.patient.Patient;
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
