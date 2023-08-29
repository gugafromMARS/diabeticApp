package mindera.mindswap.personalproject.dto.register;

import java.time.LocalDateTime;

public class RegisterDto {

    private Long id;
    private int glucose;
    private LocalDateTime date;
    private int carboHydrates;
    private int insulin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCarboHydrates() {
        return carboHydrates;
    }

    public void setCarboHydrates(int carboHydrates) {
        this.carboHydrates = carboHydrates;
    }

    public int getInsulin() {
        return insulin;
    }

    public void setInsulin(int insulin) {
        this.insulin = insulin;
    }
}
