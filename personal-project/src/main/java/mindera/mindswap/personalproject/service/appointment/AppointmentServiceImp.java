package mindera.mindswap.personalproject.service.appointment;


import mindera.mindswap.personalproject.converter.appointment.AppointmentConverter;
import mindera.mindswap.personalproject.dto.appointment.AppointmentUpdateDto;
import mindera.mindswap.personalproject.converter.doctor.DoctorConverter;
import mindera.mindswap.personalproject.converter.patient.PatientConverter;
import mindera.mindswap.personalproject.dto.appointment.AppointmentCreateDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.repository.appointment.AppointmentRepository;
import mindera.mindswap.personalproject.repository.doctor.DoctorRepository;
import mindera.mindswap.personalproject.repository.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private AppointmentConverter appointmentConverter;
    private PatientRepository patientRepository;
    private PatientConverter patientConverter;
    private DoctorConverter doctorConverter;


    @Autowired
    public AppointmentServiceImp(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, AppointmentConverter appointmentConverter, PatientRepository patientRepository, PatientConverter patientConverter, DoctorConverter doctorConverter) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentConverter = appointmentConverter;
        this.patientRepository = patientRepository;
        this.patientConverter = patientConverter;
        this.doctorConverter = doctorConverter;
    }

    @Override
    public List<AppointmentDto> getAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> appointmentConverter.toDto(appointment)).toList();
    }

    @Override
    public AppointmentDto getById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).stream()
                .map(appointment -> appointmentConverter.toDto(appointment))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    @Override
    public List<AppointmentDto> getAllByPatientId(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return patient.getAppointments().stream()
                .map(appointment -> appointmentConverter.toDto(appointment)).toList();
    }

    @Override
    public List<AppointmentDto> getAllByDoctorId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        return doctor.getAppointments().stream()
                .map(appointment -> appointmentConverter.toDto(appointment)).toList();
    }

    @Override
    public AppointmentDto create(Long doctorId, Long patientId, AppointmentCreateDto appointmentCreateDto) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        Appointment existingAppointment = appointmentRepository.findByDoctorIdPatientIdAndDate(doctorId,
                patientId,
                appointmentCreateDto.getLocalDate());
        if (existingAppointment != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Appointment already exists");
        }
        Appointment newAppointment = appointmentConverter.fromCreateDto(appointmentCreateDto,
                patient, doctor);
        appointmentRepository.save(newAppointment);

        return appointmentConverter.toDto(newAppointment);
    }

    @Override
    public void delete(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        appointmentRepository.delete(appointment);
    }


    @Override
    public AppointmentDto update(Long appointmentId, AppointmentUpdateDto appointmentUpdateDto) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        appointment.setLocalDate(appointmentUpdateDto.getLocalDate());
        appointmentRepository.save(appointment);
        return appointmentConverter.toDto(appointment);
    }
}