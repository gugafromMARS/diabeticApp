package mindera.mindswap.personalproject.dto.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private Long id;
    private int glucose;
    private LocalDateTime localDateTime;
    private double carboHydrates;
    private double insulin;


}
