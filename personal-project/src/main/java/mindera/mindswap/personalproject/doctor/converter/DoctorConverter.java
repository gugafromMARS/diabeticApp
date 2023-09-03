package mindera.mindswap.personalproject.doctor.converter;

import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.model.Doctor;
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
                .name(doctorCreateDto.getName())
                .age(doctorCreateDto.getAge())
                .email(doctorCreateDto.getEmail())
                .address(doctorCreateDto.getAddress())
                .speciality(doctorCreateDto.getSpeciality())
                .build();
    }
}
