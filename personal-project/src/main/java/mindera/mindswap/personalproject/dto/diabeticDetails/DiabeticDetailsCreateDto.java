package mindera.mindswap.personalproject.dto.diabeticDetails;

import lombok.Getter;
import lombok.Setter;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticType;
import mindera.mindswap.personalproject.model.insulin.Insulin;

import java.util.List;


@Getter
@Setter
public class DiabeticDetailsCreateDto {

    private double insulinPerCarbohydrate;
    private List<Insulin> insulinList;
    private DiabeticType diabeticType;
}
