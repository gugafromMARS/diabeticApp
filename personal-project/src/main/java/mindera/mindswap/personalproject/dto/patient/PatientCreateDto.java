package mindera.mindswap.personalproject.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
