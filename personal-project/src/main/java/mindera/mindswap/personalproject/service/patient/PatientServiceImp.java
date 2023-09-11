package mindera.mindswap.personalproject.service.patient;


import mindera.mindswap.personalproject.analytics.AnalyticsHistory;
import mindera.mindswap.personalproject.dto.analytics.AnalyticsDto;
import mindera.mindswap.personalproject.dto.analytics.DateFiltersCreateDto;
import mindera.mindswap.personalproject.converter.appointment.AppointmentConverter;
import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.repository.appointment.AppointmentRepository;
import mindera.mindswap.personalproject.converter.diabeticDetails.DiabeticDetailsConverter;
import mindera.mindswap.personalproject.dto.diabeticDetails.DiabeticDetailsCreateDto;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.repository.diabeticDetails.DiabeticDetailsRepository;
import mindera.mindswap.personalproject.converter.patient.PatientConverter;
import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.dto.patient.PatientUpdateDto;
import mindera.mindswap.personalproject.model.insulin.Insulin;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.repository.insulin.InsulinRepository;
import mindera.mindswap.personalproject.repository.patient.PatientRepository;
import mindera.mindswap.personalproject.converter.register.RegisterConverter;
import mindera.mindswap.personalproject.dto.register.RegisterDto;
import mindera.mindswap.personalproject.repository.register.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PatientServiceImp implements PatientService {

    PatientRepository patientRepository;
    PatientConverter patientConverter;
    InsulinRepository insulinRepository;
    DiabeticDetailsConverter diabeticDetailsConverter;
    DiabeticDetailsRepository diabeticDetailsRepository;
    AppointmentConverter appointmentConverter;
    AppointmentRepository appointmentRepository;
    RegisterConverter registerConverter;
    RegisterRepository registerRepository;
    AnalyticsHistory analyticsHistory;

    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, PatientConverter patientConverter, InsulinRepository insulinRepository, DiabeticDetailsConverter diabeticDetailsConverter, DiabeticDetailsRepository diabeticDetailsRepository, AppointmentConverter appointmentConverter, AppointmentRepository appointmentRepository, RegisterConverter registerConverter, RegisterRepository registerRepository, AnalyticsHistory analyticsHistory) {
        this.patientRepository = patientRepository;
        this.patientConverter = patientConverter;
        this.insulinRepository = insulinRepository;
        this.diabeticDetailsConverter = diabeticDetailsConverter;
        this.diabeticDetailsRepository = diabeticDetailsRepository;
        this.appointmentConverter = appointmentConverter;
        this.appointmentRepository = appointmentRepository;
        this.registerConverter = registerConverter;
        this.registerRepository = registerRepository;
        this.analyticsHistory = analyticsHistory;
    }

    public List<PatientDto> getAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> patientConverter.toDto(patient)).toList();
    }




    public PatientDto getById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return patientConverter.toDto(patient);
    }

    public PatientDto create(PatientCreateDto patientCreateDto) {
        Patient patient = patientRepository.findByEmail(patientCreateDto.getEmail());
        if(patient != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!");
        }
        Patient newPatient = patientConverter.fromCreateDtoToUser(patientCreateDto);
        patientRepository.save(newPatient);
        return patientConverter.toDto(newPatient);
    }

    public void delete(Long userId) {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        patientRepository.delete(patient);
    }

    public void deleteInsulinById(Long userId, Long insulinId) {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Insulin insulin = insulinRepository.findById(insulinId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insulin not found"));
        patient.getDiabeticDetails().getInsulinList().remove(insulin);
        patientRepository.save(patient);
        insulinRepository.delete(insulin);
    }


    public PatientDto update(Long userId, PatientUpdateDto patientUpdateDto) {
        Patient existingPatient = patientRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        if(patientUpdateDto.getInsulin() != null) {
            for(Insulin insulin : existingPatient.getDiabeticDetails().getInsulinList()){
                if (insulin.getName().equals(patientUpdateDto.getInsulin().getName())){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already have this insulin");
                }
            }
            existingPatient.getDiabeticDetails().getInsulinList().add(patientUpdateDto.getInsulin());
        }
        existingPatient.getDiabeticDetails().setInsulinPerCarbohydrate(patientUpdateDto.getInsulinPerCarbohydrate());
        patientRepository.save(existingPatient);
        return patientConverter.toDto(existingPatient);
    }

    public PatientDto createDiabeticDetails(Long patientId, DiabeticDetailsCreateDto diabeticDetailsCreateDto) {
            Patient existingPatient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
            DiabeticDetails diabeticDetails = diabeticDetailsConverter.fromCreateDto(diabeticDetailsCreateDto);
            diabeticDetailsRepository.save(diabeticDetails);
            existingPatient.setDiabeticDetails(diabeticDetails);
            patientRepository.save(existingPatient);
            return patientConverter.toDto(existingPatient);
    }

    public List<AppointmentDto> getAllAppointments(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return patient.getAppointments().stream()
                .map(appointment -> appointmentConverter.toDto(appointment)).toList();
    }

    public AppointmentDto getAppointmentById(Long patientId, Long appointmentId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found"));
        return appointmentConverter.toDto(appointment);
    }

    public List<RegisterDto> getAllRegisters(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return registerRepository.findAllByPatientId(patientId).stream()
                .map(register -> registerConverter.toDto(register)).toList();
    }

    public RegisterDto getRegisterById(Long patientId, Long registerId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return registerRepository.findById(registerId).stream()
                .map(register -> registerConverter.toDto(register))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Register not found"));
    }

    public List<RegisterDto> getRegisterByDate(Long patientId, DateFiltersCreateDto dateFiltersCreateDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
            return analyticsHistory.registersBetweenDates(patientId,
                    dateFiltersCreateDto.getStartDate(),
                    dateFiltersCreateDto.getEndDate()).stream()
                    .map(register -> registerConverter.toDto(register)).toList();
    }

    public AnalyticsDto getAvgRegisters(Long patientId, DateFiltersCreateDto dateFiltersCreateDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        return analyticsHistory.avgGlucoseAndInsulin(patientId, dateFiltersCreateDto.getStartDate(), dateFiltersCreateDto.getEndDate());
    }
}
