package mindera.mindswap.personalproject.appointment.converter;


import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConverter {


    public AppointmentDto toDto(Appointment appointment){
        return new AppointmentDto(
                appointment.getLocalDateTime(),
                appointment.getPatient(),
                appointment.getDoctor(),
                appointment.getInstitution());
    }

//    public Appointment fromCreateDto(AppointmentCreateDto appointmentCreateDto){
//        return Appointment.builder()
//                .withLocalDate(appointmentCreateDto.getLocalDateTime())
//                .withPatient(appointmentCreateDto.getPatient())
//                .withDoctor(appointmentCreateDto.getDoctor())
//                .withInstitution(appointmentCreateDto.getInstitution())
//                .withDescription(appointmentCreateDto.getDescription())
//                .build();
//    }
}
