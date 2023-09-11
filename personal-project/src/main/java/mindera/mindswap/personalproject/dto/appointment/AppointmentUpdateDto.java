package mindera.mindswap.personalproject.dto.appointment;

import java.time.LocalDate;

public class AppointmentUpdateDto {

    public LocalDate localDate;

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
