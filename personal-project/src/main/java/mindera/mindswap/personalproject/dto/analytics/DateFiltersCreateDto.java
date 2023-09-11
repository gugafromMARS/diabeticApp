package mindera.mindswap.personalproject.dto.analytics;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class DateFiltersCreateDto {

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
