package mindera.mindswap.personalproject.register.dto;

import java.time.LocalDateTime;

public class RegisterCreateDto {

    private int glucose;
    private LocalDateTime date;
    private int carboHydrates;
    private int insulin;

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
