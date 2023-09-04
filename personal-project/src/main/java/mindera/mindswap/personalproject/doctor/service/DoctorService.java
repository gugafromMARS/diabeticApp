package mindera.mindswap.personalproject.doctor.service;


import mindera.mindswap.personalproject.doctor.converter.DoctorConverter;
import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorUpdateDto;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class DoctorService {

    DoctorRepository doctorRepository;
    DoctorConverter doctorConverter;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DoctorConverter doctorConverter) {
        this.doctorRepository = doctorRepository;
        this.doctorConverter = doctorConverter;
    }



    public DoctorDto getById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        return doctorConverter.toDto(doctor);
    }

    public DoctorDto create(DoctorCreateDto doctorCreateDto) {
        Doctor doctor = doctorRepository.findByEmail(doctorCreateDto.getEmail());
        if(doctor != null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor already exists");
        }
        Doctor newDoctor = doctorConverter.fromDoctorCreateDto(doctorCreateDto);
        doctorRepository.save(newDoctor);
        return doctorConverter.toDto(newDoctor);
    }

    public ResponseEntity<String> delete(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        doctorRepository.delete(doctor);
        return ResponseEntity.ok().build();
    }

    public DoctorDto update(Long doctorId, DoctorUpdateDto doctorUpdateDto) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        if(doctorUpdateDto.getEmail() != null){
            doctor.setEmail(doctorUpdateDto.getEmail());
        }
        if(doctorUpdateDto.getHabitation() != null){
            doctor.setAddress(doctorUpdateDto.getHabitation());
        }
        doctorRepository.save(doctor);
        return doctorConverter.toDto(doctor);
    }
}