package mindera.mindswap.personalproject.calculator;


import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public double calculateInsulin(double carboHydrates, double insulinPerCarboHydrate){
        return  (carboHydrates / 15) * insulinPerCarboHydrate;
    }

}
