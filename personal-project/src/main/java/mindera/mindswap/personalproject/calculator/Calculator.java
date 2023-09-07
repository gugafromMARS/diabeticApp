package mindera.mindswap.personalproject.calculator;


import mindera.mindswap.personalproject.register.model.Register;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Calculator {

    public double calculateInsulin(double carboHydrates, double insulinPerCarboHydrate){
        return  (carboHydrates / 15) * insulinPerCarboHydrate;
    }

    public int calculateAvgGlucose(List<Register> registers){
        int total = 0;
        List<Integer> glucoseRegisters = registers.stream().map(register -> register.getGlucose()).toList();
        for(Integer glucose : glucoseRegisters){
            total += glucose;
        }
        return avg(glucoseRegisters.size(), total);
    }

    public int calculateAvgInsulin(List<Register> registers){
        int total = 0;
        List<Double> insulinRegisters = registers.stream().map(register -> register.getInsulin()).toList();
        for(Double insulin : insulinRegisters){
            total += insulin;
        }
        return avg(insulinRegisters.size(), total);
    }

    public int avg(int sizeList, int total){
        return total/sizeList;
    }

}
