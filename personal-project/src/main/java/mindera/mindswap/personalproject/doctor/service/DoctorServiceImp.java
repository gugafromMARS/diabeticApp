package mindera.mindswap.personalproject.doctor.service;


import mindera.mindswap.personalproject.appointment.converter.AppointmentConverter;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.model.Appointment;
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

import java.util.List;


@Service
public class DoctorServiceImp implements DoctorService {

    DoctorRepository doctorRepository;
    DoctorConverter doctorConverter;
    AppointmentConverter appointmentConverter;
    @Autowired
    public DoctorServiceImp(DoctorRepository doctorRepository, DoctorConverter doctorConverter, AppointmentConverter appointmentConverter) {
        this.doctorRepository = doctorRepository;
        this.doctorConverter = doctorConverter;
        this.appointmentConverter = appointmentConverter;
    }

    @Override
    public DoctorDto getById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        return doctorConverter.toDto(doctor);
    }

    @Override
    public DoctorDto create(DoctorCreateDto doctorCreateDto) {
        Doctor doctor = doctorRepository.findByEmail(doctorCreateDto.getEmail());
        if(doctor != null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor already exists");
        }
        Doctor newDoctor = doctorConverter.fromDoctorCreateDto(doctorCreateDto);
        doctorRepository.save(newDoctor);
        return doctorConverter.toDto(newDoctor);
    }

    @Override
    public ResponseEntity<String> delete(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        doctorRepository.delete(doctor);
        return ResponseEntity.ok().build();
    }

    @Override
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

    @Override
    public List<AppointmentDto> getAppointments(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        return doctor.getAppointments().stream()
                .map(appointment -> appointmentConverter.toDto(appointment)).toList();
    }

    @Override
    public AppointmentDto getAppointmentById(Long doctorId, Long appointmentId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        Appointment appointment = doctor.getAppointments().stream().filter(a -> a.getId().equals(appointmentId))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        return appointmentConverter.toDto(appointment);
    }
}
