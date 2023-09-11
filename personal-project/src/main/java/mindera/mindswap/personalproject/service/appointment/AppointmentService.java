package mindera.mindswap.personalproject.service.appointment;

import mindera.mindswap.personalproject.dto.appointment.AppointmentCreateDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentUpdateDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> getAll();

    AppointmentDto getById(Long appointmentId);

    List<AppointmentDto> getAllByPatientId(Long patientId);

    List<AppointmentDto> getAllByDoctorId(Long doctorId);

    AppointmentDto create(Long doctorId, Long patientId, AppointmentCreateDto appointmentCreateDto);

    void delete(Long appointmentId);

    AppointmentDto update(Long appointmentId, AppointmentUpdateDto appointmentUpdateDto);
}
