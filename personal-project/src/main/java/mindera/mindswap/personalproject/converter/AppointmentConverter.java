package mindera.mindswap.personalproject.converter;


import mindera.mindswap.personalproject.dto.appointment.AppointmentCreateDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter {


    public AppointmentDto toDto(Appointment appointment){
        return new AppointmentDto(appointment.getId(),
                appointment.getLocalDateTime(),
                appointment.getPatient(),
                appointment.getDoctor(),
                appointment.getInstitution());
    }

    public Appointment fromCreateDto(AppointmentCreateDto appointmentCreateDto){
        return Appointment.builder()
                .withLocalDate(appointmentCreateDto.getLocalDateTime())
                .withPatient(appointmentCreateDto.getPatient())
                .withDoctor(appointmentCreateDto.getDoctor())
                .withInstitution(appointmentCreateDto.getInstitution())
                .withDescription(appointmentCreateDto.getDescription())
                .build();
    }
}
