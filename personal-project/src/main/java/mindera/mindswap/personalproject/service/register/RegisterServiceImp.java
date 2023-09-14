package mindera.mindswap.personalproject.service.register;


import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.repository.patient.PatientRepository;
import mindera.mindswap.personalproject.converter.register.RegisterConverter;
import mindera.mindswap.personalproject.dto.register.RegisterCreateDto;
import mindera.mindswap.personalproject.dto.register.RegisterDto;
import mindera.mindswap.personalproject.dto.register.RegisterUpdateDto;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.repository.register.RegisterRepository;
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
        if(registerUpdateDto.getCarboHydrates() == 0 && registerUpdateDto.getGlucose() != 0){
            existingRegister.setGlucose(registerUpdateDto.getGlucose());
        }
        if(registerUpdateDto.getGlucose() == 0 && registerUpdateDto.getCarboHydrates() != 0){
            existingRegister.setCarboHydrates(registerUpdateDto.getCarboHydrates());
        }
        registerRepository.save(existingRegister);
        return registerConverter.toDto(existingRegister);

    }
}
