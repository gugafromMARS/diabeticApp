package mindera.mindswap.personalproject.appointment.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentCreateDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDateTime;
    private String description;

    public LocalDate getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDate localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
