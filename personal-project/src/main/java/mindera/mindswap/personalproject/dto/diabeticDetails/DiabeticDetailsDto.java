package mindera.mindswap.personalproject.dto.diabeticDetails;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticType;
import mindera.mindswap.personalproject.model.insulin.Insulin;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiabeticDetailsDto {

    private Long id;

    private double insulinPerCarbohydrate;

    private List<Insulin> insulinList;

    private DiabeticType diabeticType;

}
