package mindera.mindswap.personalproject.dto.appointment;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public class AppointmentCreateDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate localDate;
    private String description;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
