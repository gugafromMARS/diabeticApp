package mindera.mindswap.personalproject.diabeticDetails.dto;

import lombok.Getter;
import lombok.Setter;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticType;
import mindera.mindswap.personalproject.insulin.model.Insulin;

import java.util.List;


@Getter
@Setter
public class DiabeticDetailsCreateDto {

    private double insulinPerCarbohydrate;
    private List<Insulin> insulinList;
    private DiabeticType diabeticType;
}
