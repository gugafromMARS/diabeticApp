package mindera.mindswap.personalproject.diabeticDetails.converter;

import mindera.mindswap.personalproject.diabeticDetails.dto.DiabeticDetailsCreateDto;
import mindera.mindswap.personalproject.diabeticDetails.dto.DiabeticDetailsDto;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticDetails;
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
