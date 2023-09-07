package mindera.mindswap.personalproject.doctor.service;

import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    DoctorDto getById(Long doctorId);

    DoctorDto create(DoctorCreateDto doctorCreateDto);

    ResponseEntity<String> delete(Long doctorId);

    DoctorDto update(Long doctorId, DoctorUpdateDto doctorUpdateDto);

    List<AppointmentDto> getAppointments(Long doctorId);

    AppointmentDto getAppointmentById(Long doctorId, Long appointmentId);
}
