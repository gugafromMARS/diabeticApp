package mindera.mindswap.personalproject.patient.service;


import mindera.mindswap.personalproject.appointment.converter.AppointmentConverter;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.model.Appointment;
import mindera.mindswap.personalproject.appointment.repository.AppointmentRepository;
import mindera.mindswap.personalproject.diabeticDetails.converter.DiabeticDetailsConverter;
import mindera.mindswap.personalproject.diabeticDetails.dto.DiabeticDetailsCreateDto;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticDetails;
import mindera.mindswap.personalproject.diabeticDetails.repository.DiabeticDetailsRepository;
import mindera.mindswap.personalproject.patient.converter.PatientConverter;
import mindera.mindswap.personalproject.patient.dto.PatientCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientDto;
import mindera.mindswap.personalproject.patient.dto.PatientUpdateDto;
import mindera.mindswap.personalproject.insulin.model.Insulin;
import mindera.mindswap.personalproject.patient.model.Patient;
import mindera.mindswap.personalproject.insulin.repository.InsulinRepository;
import mindera.mindswap.personalproject.patient.repository.PatientRepository;
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

    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, PatientConverter patientConverter, InsulinRepository insulinRepository, DiabeticDetailsConverter diabeticDetailsConverter, DiabeticDetailsRepository diabeticDetailsRepository, AppointmentConverter appointmentConverter, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.patientConverter = patientConverter;
        this.insulinRepository = insulinRepository;
        this.diabeticDetailsConverter = diabeticDetailsConverter;
        this.diabeticDetailsRepository = diabeticDetailsRepository;
        this.appointmentConverter = appointmentConverter;
        this.appointmentRepository = appointmentRepository;
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
}
