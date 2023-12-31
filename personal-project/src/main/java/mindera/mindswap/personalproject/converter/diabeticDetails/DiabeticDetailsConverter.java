package mindera.mindswap.personalproject.converter.diabeticDetails;

import mindera.mindswap.personalproject.dto.diabeticDetails.DiabeticDetailsCreateDto;
import mindera.mindswap.personalproject.dto.diabeticDetails.DiabeticDetailsDto;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import org.springframework.stereotype.Component;

@Component
public class DiabeticDetailsConverter {

    public DiabeticDetailsDto toDto(DiabeticDetails diabeticDetails){
        return new DiabeticDetailsDto(diabeticDetails.getId(),
                diabeticDetails.getInsulinPerCarbohydrate(),
                diabeticDetails.getInsulinList(),
                diabeticDetails.getDiabeticType());
    }
    public DiabeticDetails fromCreateDto(DiabeticDetailsCreateDto diabeticDetailsCreateDto){
        return DiabeticDetails.builder()
                .insulinPerCarbohydrate(diabeticDetailsCreateDto.getInsulinPerCarbohydrate())
                .insulinList(diabeticDetailsCreateDto.getInsulinList())
                .diabeticType(diabeticDetailsCreateDto.getDiabeticType())
                .build();
    }
}
