package mindera.mindswap.personalproject.service.doctor;

import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorCreateDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorUpdateDto;
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
