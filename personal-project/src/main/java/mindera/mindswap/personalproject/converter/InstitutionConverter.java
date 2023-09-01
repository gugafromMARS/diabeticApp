package mindera.mindswap.personalproject.converter;


import mindera.mindswap.personalproject.dto.institution.InstitutionCreateDto;
import mindera.mindswap.personalproject.dto.institution.InstitutionDto;
import mindera.mindswap.personalproject.model.institution.Institution;
import org.springframework.stereotype.Component;

@Component
public class InstitutionConverter {


    public InstitutionDto toDto(Institution institution){
        return new InstitutionDto(institution.getId(),
                institution.getName(),
                institution.getAddress(),
                institution.getEmail(),
                institution.getType());
    }

    public Institution fromCreateDto(InstitutionCreateDto institutionCreateDto){
        return Institution.builder()
                .withName(institutionCreateDto.getName())
                .withAddress(institutionCreateDto.getAddress())
                .withEmail(institutionCreateDto.getEmail())
                .withType(institutionCreateDto.getInstitutionType())
                .build();
    }

}
