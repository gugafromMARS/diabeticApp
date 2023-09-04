package mindera.mindswap.personalproject.diabeticDetails.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticType;
import mindera.mindswap.personalproject.insulin.model.Insulin;

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
