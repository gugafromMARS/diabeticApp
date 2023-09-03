package mindera.mindswap.personalproject.patient.converter;

import mindera.mindswap.personalproject.patient.dto.PatientCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientDto;
import mindera.mindswap.personalproject.patient.model.Patient;
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
                .name(patientCreateDto.getName())
                .email(patientCreateDto.getEmail())
                .age(patientCreateDto.getAge())
                .height(patientCreateDto.getHeight())
                .weight(patientCreateDto.getWeight())
                .diabeticDetails(patientCreateDto.getDiabeticDetails())
                .build();

    }
}
