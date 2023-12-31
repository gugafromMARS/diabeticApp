package mindera.mindswap.personalproject.dto.patient;

import mindera.mindswap.personalproject.model.insulin.Insulin;

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
