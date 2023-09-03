package mindera.mindswap.personalproject.patient.dto;

import mindera.mindswap.personalproject.insulin.model.Insulin;

public class PatientUpdateDto {

    private double insulinPerCarbohydrate;
    private Insulin insulin;

    public double getInsulinPerCarbohydrate() {
        return insulinPerCarbohydrate;
    }

    public void setInsulinPerCarbohydrate(double insulinPerCarbohydrate) {
        this.insulinPerCarbohydrate = insulinPerCarbohydrate;
    }

    public Insulin getInsulin() {
        return insulin;
    }

    public void setInsulin(Insulin insulin) {
        this.insulin = insulin;
    }
}
