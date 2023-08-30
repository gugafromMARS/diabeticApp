package mindera.mindswap.personalproject.converter;

import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.model.user.Patient;
import org.springframework.stereotype.Component;


@Component
public class PatientConverter {

    public PatientDto toDto(Patient patient){
        return new PatientDto(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getAge(),
                patient.getDiabeticDetails());
    }

    public Patient fromCreateDtoToUser(PatientCreateDto patientCreateDto){
        return Patient.builder()
                .withName(patientCreateDto.getName())
                .withEmail(patientCreateDto.getEmail())
                .withAge(patientCreateDto.getAge())
                .withHeight(patientCreateDto.getHeight())
                .withWeight(patientCreateDto.getWeight())
                .withDiabeticDetails(patientCreateDto.getDiabeticDetails())
                .withRegisters(patientCreateDto.getRegisterList())
                .build();

    }
}
