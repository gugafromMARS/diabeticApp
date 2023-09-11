package mindera.mindswap.personalproject.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mindera.mindswap.personalproject.dto.diabeticDetails.DiabeticDetailsDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Long id;
    private String name;
    private String email;
    private int age;
    private DiabeticDetailsDto diabeticDetailsDto;


    public PatientDto(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
