package mindera.mindswap.personalproject.analitics.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class DateFiltersCreateDto {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
