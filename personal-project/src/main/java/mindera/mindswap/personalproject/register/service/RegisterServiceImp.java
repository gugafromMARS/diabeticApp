package mindera.mindswap.personalproject.register.service;


import mindera.mindswap.personalproject.patient.model.Patient;
import mindera.mindswap.personalproject.patient.repository.PatientRepository;
import mindera.mindswap.personalproject.register.converter.RegisterConverter;
import mindera.mindswap.personalproject.register.dto.RegisterCreateDto;
import mindera.mindswap.personalproject.register.dto.RegisterDto;
import mindera.mindswap.personalproject.register.dto.RegisterUpdateDto;
import mindera.mindswap.personalproject.register.model.Register;
import mindera.mindswap.personalproject.register.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegisterServiceImp implements RegisterService {

    private RegisterRepository registerRepository;
    private PatientRepository patientRepository;
    private RegisterConverter registerConverter;

    @Autowired
    public RegisterServiceImp(RegisterRepository registerRepository, PatientRepository patientRepository, RegisterConverter registerConverter) {
        this.registerRepository = registerRepository;
        this.patientRepository = patientRepository;
        this.registerConverter = registerConverter;
    }



    @Override
    public List<RegisterDto> getAll() {
        return registerRepository.findAll().stream()
                .map(register -> registerConverter.toDto(register)).toList();
    }

    @Override
    public RegisterDto create(RegisterCreateDto registerCreateDto, Long patientId) {
        Register existingRegister = registerRepository.findByLocalDateTime(registerCreateDto.getLocalDateTime());
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        if(existingRegister != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Register already exists");
        }
        Register newRegister = registerConverter.fromCreateDto(registerCreateDto, patient);
        registerRepository.save(newRegister);
        return registerConverter.toDto(newRegister);
    }

    @Override
    public void deleteById(Long registerId) {
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Register not found"));
        registerRepository.delete(register);
    }

    @Override
    public RegisterDto update(Long registerId, RegisterUpdateDto registerUpdateDto) {
        Register existingRegister = registerRepository.findById(registerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Register not found"));
        if(registerUpdateDto.getCarboHydrates() == 0 && registerUpdateDto.getGlucose() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if(registerUpdateDto.getCarboHydrates() != 0 && registerUpdateDto.getGlucose() != 0){
            existingRegister.setGlucose(registerUpdateDto.getGlucose());
            existingRegister.setCarboHydrates(registerUpdateDto.getCarboHydrates());
        }
        if(registerUpdateDto.getCarboHydrates() == 0){
            existingRegister.setGlucose(registerUpdateDto.getGlucose());
        }
        if(registerUpdateDto.getGlucose() == 0){
            existingRegister.setCarboHydrates(registerUpdateDto.getCarboHydrates());
        }
        registerRepository.save(existingRegister);
        return registerConverter.toDto(existingRegister);

    }
}
