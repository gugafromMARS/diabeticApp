package mindera.mindswap.personalproject.converter.register;


import mindera.mindswap.personalproject.calculator.Calculator;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.dto.register.RegisterCreateDto;
import mindera.mindswap.personalproject.dto.register.RegisterDto;
import mindera.mindswap.personalproject.model.register.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {

    private Calculator calculator;

    @Autowired
    public RegisterConverter(Calculator calculator) {
        this.calculator = calculator;
    }

    public RegisterDto toDto(Register register){
        return new RegisterDto(register.getId(),
                register.getGlucose(),
                register.getLocalDateTime(),
                register.getCarboHydrates(),
                register.getInsulin());
    }

    public Register fromCreateDto(RegisterCreateDto registerCreateDto, Patient patient){
        return Register.builder()
                .glucose(registerCreateDto.getGlucose())
                .localDateTime(registerCreateDto.getLocalDateTime())
                .carboHydrates(registerCreateDto.getCarboHydrates())
                .insulin(calculator.calculateInsulin(registerCreateDto.getCarboHydrates(), patient.getDiabeticDetails().getInsulinPerCarbohydrate()))
                .patient(patient)
                .build();
    }

}
