package mindera.mindswap.personalproject.converter.doctor;

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
                .name(doctorCreateDto.getName())
                .age(doctorCreateDto.getAge())
                .email(doctorCreateDto.getEmail())
                .address(doctorCreateDto.getAddress())
                .speciality(doctorCreateDto.getSpeciality())
                .build();
    }
}
