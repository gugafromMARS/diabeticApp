package mindera.mindswap.personalproject.service.register;

import mindera.mindswap.personalproject.dto.register.RegisterCreateDto;
import mindera.mindswap.personalproject.dto.register.RegisterDto;
import mindera.mindswap.personalproject.dto.register.RegisterUpdateDto;

import java.util.List;

public interface RegisterService {
    List<RegisterDto> getAll();

    RegisterDto create(RegisterCreateDto registerCreateDto, Long patientId);

    void deleteById(Long registerId);

    RegisterDto update(Long registerId, RegisterUpdateDto registerUpdateDto);
}
