package mindera.mindswap.personalproject.calculator;

public class Calculator {

    public static int calculateInsulin(int carboHydrates, int insulinPerCarboHydrate){
        return  (carboHydrates / 15) * insulinPerCarboHydrate;
    }

}
