package mindera.mindswap.personalproject.dto.analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int avgGlucose;
    private int avgInsulin;
}
