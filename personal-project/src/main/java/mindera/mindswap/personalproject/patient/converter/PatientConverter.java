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
                .withName(patientCreateDto.getName())
                .withEmail(patientCreateDto.getEmail())
                .withAge(patientCreateDto.getAge())
                .withHeight(patientCreateDto.getHeight())
                .withWeight(patientCreateDto.getWeight())
                .withDiabeticDetails(patientCreateDto.getDiabeticDetails())
                .build();

    }
}
