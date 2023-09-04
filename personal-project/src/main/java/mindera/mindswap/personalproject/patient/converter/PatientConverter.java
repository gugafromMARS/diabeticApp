package mindera.mindswap.personalproject.patient.converter;

import mindera.mindswap.personalproject.diabeticDetails.converter.DiabeticDetailsConverter;
import mindera.mindswap.personalproject.patient.dto.PatientCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientDto;
import mindera.mindswap.personalproject.patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatientConverter {

    private DiabeticDetailsConverter diabeticDetailsConverter;
    @Autowired
    public PatientConverter(DiabeticDetailsConverter diabeticDetailsConverter) {
        this.diabeticDetailsConverter = diabeticDetailsConverter;
    }

    public PatientDto toDto(Patient patient){
        if(patient.getDiabeticDetails() != null){
            return new PatientDto(patient.getId(),
                    patient.getName(),
                    patient.getEmail(),
                    patient.getAge(),
                    diabeticDetailsConverter.toDto(patient.getDiabeticDetails()));
        }
        return new PatientDto(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getAge());
    }

    public Patient fromCreateDtoToUser(PatientCreateDto patientCreateDto){
        return Patient.builder()
                .name(patientCreateDto.getName())
                .email(patientCreateDto.getEmail())
                .age(patientCreateDto.getAge())
                .height(patientCreateDto.getHeight())
                .weight(patientCreateDto.getWeight())
                .build();

    }
}
