package mindera.mindswap.personalproject.register.service;

import mindera.mindswap.personalproject.register.dto.RegisterCreateDto;
import mindera.mindswap.personalproject.register.dto.RegisterDto;
import mindera.mindswap.personalproject.register.dto.RegisterUpdateDto;

import java.util.List;

public interface RegisterService {
    List<RegisterDto> getAll();

    RegisterDto create(RegisterCreateDto registerCreateDto, Long patientId);

    void deleteById(Long registerId);

    RegisterDto update(Long registerId, RegisterUpdateDto registerUpdateDto);
}
