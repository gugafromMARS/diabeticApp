package mindera.mindswap.personalproject.converter.patient;

import mindera.mindswap.personalproject.converter.diabeticDetails.DiabeticDetailsConverter;
import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.model.patient.Patient;
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
