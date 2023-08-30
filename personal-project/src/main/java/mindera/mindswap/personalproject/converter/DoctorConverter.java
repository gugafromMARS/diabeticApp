package mindera.mindswap.personalproject.converter;

import mindera.mindswap.personalproject.dto.doctor.DoctorCreateDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorDto;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import org.springframework.stereotype.Component;


@Component
public class DoctorConverter {

    public DoctorDto toDto(Doctor doctor){
        return new DoctorDto(doctor.getId(),
                doctor.getName(),
                doctor.getAge(),
                doctor.getEmail(),
                doctor.getSpeciality());
    }

    public Doctor fromDoctorCreateDto(DoctorCreateDto doctorCreateDto){
        return Doctor.builder()
                .withName(doctorCreateDto.getName())
                .withAge(doctorCreateDto.getAge())
                .withEmail(doctorCreateDto.getEmail())
                .withHabitation(doctorCreateDto.getHabitation())
                .withSpeciality(doctorCreateDto.getSpeciality())
                .withAppointments(doctorCreateDto.getAppointments())
                .build();
    }
}
