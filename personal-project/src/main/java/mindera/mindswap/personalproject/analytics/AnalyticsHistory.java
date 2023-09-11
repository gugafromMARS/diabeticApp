package mindera.mindswap.personalproject.analytics;


import mindera.mindswap.personalproject.dto.analytics.AnalyticsDto;
import mindera.mindswap.personalproject.calculator.Calculator;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.repository.register.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AnalyticsHistory {

    private Calculator calculator;

    private RegisterRepository registerRepository;

    @Autowired
    public AnalyticsHistory(Calculator calculator, RegisterRepository registerRepository) {
        this.calculator = calculator;
        this.registerRepository = registerRepository;
    }

    public AnalyticsDto avgGlucoseAndInsulin(Long patientId, LocalDateTime startDate, LocalDateTime endDate){
        List<Register> registers = registerRepository.findByLocalDateTimeBetween(startDate, endDate).stream()
                .filter(register -> register.getPatient().getId().equals(patientId)).toList();
        int avgGlucose = calculator.calculateAvgGlucose(registers);
        int avgInsulin = calculator.calculateAvgInsulin(registers);
        return new AnalyticsDto(startDate, endDate, avgGlucose, avgInsulin);
    }

    public List<Register> registersBetweenDates(Long patientId, LocalDateTime startDate, LocalDateTime endDate){
        return registerRepository.findByLocalDateTimeBetween(startDate, endDate).stream()
                .filter(register -> register.getPatient().getId().equals(patientId)).toList();
    }


}
