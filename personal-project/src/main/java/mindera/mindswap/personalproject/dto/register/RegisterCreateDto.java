package mindera.mindswap.personalproject.dto.register;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
public class RegisterCreateDto {

    private int glucose;
    private LocalDateTime localDateTime;
    private double carboHydrates;

}
