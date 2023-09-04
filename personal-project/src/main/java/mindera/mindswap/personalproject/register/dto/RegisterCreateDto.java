package mindera.mindswap.personalproject.register.dto;

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
