package mindera.mindswap.personalproject.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticDetails;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientCreateDto {

    private String name;
    private String email;
    private int age;
    private double height;
    private int weight;



}
