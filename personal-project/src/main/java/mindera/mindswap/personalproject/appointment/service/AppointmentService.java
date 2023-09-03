package mindera.mindswap.personalproject.appointment.service;


import mindera.mindswap.personalproject.appointment.converter.AppointmentConverter;
import mindera.mindswap.personalproject.doctor.converter.DoctorConverter;
import mindera.mindswap.personalproject.institution.converter.InstitutionConverter;
import mindera.mindswap.personalproject.patient.converter.PatientConverter;
import mindera.mindswap.personalproject.appointment.dto.AppointmentCreateDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.model.Appointment;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.institution.model.Institution;
import mindera.mindswap.personalproject.patient.model.Patient;
import mindera.mindswap.personalproject.appointment.repository.AppointmentRepository;
import mindera.mindswap.personalproject.doctor.repository.DoctorRepository;
import mindera.mindswap.personalproject.institution.repository.InstitutionRepository;
import mindera.mindswap.personalproject.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private AppointmentConverter appointmentConverter;
    private PatientRepository patientRepository;
    private InstitutionRepository institutionRepository;

    private PatientConverter patientConverter;
    private DoctorConverter doctorConverter;
    private InstitutionConverter institutionConverter;


    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, AppointmentConverter appointmentConverter, PatientRepository patientRepository, InstitutionRepository institutionRepository, PatientConverter patientConverter, DoctorConverter doctorConverter, InstitutionConverter institutionConverter) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.appointmentConverter = appointmentConverter;
        this.patientRepository = patientRepository;
        this.institutionRepository = institutionRepository;
        this.patientConverter = patientConverter;
        this.doctorConverter = doctorConverter;
        this.institutionConverter = institutionConverter;
    }

    public List<AppointmentDto> getAll(Long doctorId) {
        return doctorRepository.findById(doctorId).get().getAppointments()
                .stream().map(appointment -> appointmentConverter.toDto(appointment)).toList();

    }

    public AppointmentDto getById(Long doctorId, Long appointmentId) {
        return doctorRepository.findById(doctorId).get().getAppointments()
                .stream().filter(appointment -> appointment.getId().equals(appointmentId))
                .map(appointment -> appointmentConverter.toDto(appointment))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
    }

    public AppointmentDto create(Long doctorId, Long patientId, AppointmentCreateDto appointmentCreateDto) {
        Long defaultInstitution = 1L;
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        Appointment appointment = appointmentRepository.findByDoctorIdPatientIdAndDate(doctorId,
                patientId,
                appointmentCreateDto.getLocalDateTime());
        Institution institution = institutionRepository.findById(defaultInstitution)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));

        if (appointment != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Appointment already exists");
        }
        Appointment newAppointment = Appointment.builder()
                .withLocalDate(appointmentCreateDto.getLocalDateTime())
                .withDoctor(doctor)
                .withPatient(patient)
                .withInstitution(institution)
                .withDescription(appointmentCreateDto.getDescription())
                .build();//appointmentConverter.fromCreateDto(appointmentCreateDto);
        doctor.getAppointments().add(newAppointment);
        appointmentRepository.save(newAppointment);

        return new AppointmentDto(newAppointment.getLocalDateTime(),
                patient, doctor, institution);

    }
}