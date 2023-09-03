package mindera.mindswap.personalproject.diabeticDetails.model;


import jakarta.persistence.*;
import mindera.mindswap.personalproject.insulin.model.Insulin;
import mindera.mindswap.personalproject.diabeticType.model.DiabeticType;

import java.util.List;

@Entity(name = "diabeticDetail")
public class DiabeticDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double insulinPerCarbohydrate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Insulin> insulinList;
    @OneToOne(cascade = CascadeType.ALL)
    private DiabeticType diabeticType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getInsulinPerCarbohydrate() {
        return insulinPerCarbohydrate;
    }

    public void setInsulinPerCarbohydrate(double insulinPerCarbohydrate) {
        this.insulinPerCarbohydrate = insulinPerCarbohydrate;
    }

    public List<Insulin> getInsulinList() {
        return insulinList;
    }

    public void setInsulinList(List<Insulin> insulinList) {
        this.insulinList = insulinList;
    }

    public DiabeticType getDiabeticType() {
        return diabeticType;
    }

    public void setDiabeticType(DiabeticType diabeticType) {
        this.diabeticType = diabeticType;
    }
}
