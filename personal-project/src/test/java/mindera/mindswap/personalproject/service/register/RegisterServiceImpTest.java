package mindera.mindswap.personalproject.service.register;

import mindera.mindswap.personalproject.converter.register.RegisterConverter;
import mindera.mindswap.personalproject.dto.register.RegisterCreateDto;
import mindera.mindswap.personalproject.dto.register.RegisterDto;
import mindera.mindswap.personalproject.dto.register.RegisterUpdateDto;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.repository.patient.PatientRepository;
import mindera.mindswap.personalproject.repository.register.RegisterRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class RegisterServiceImpTest {

    @Mock
    PatientRepository patientRepository;

    @Mock
    RegisterRepository registerRepository;

    @Mock
    RegisterConverter registerConverter;

    @InjectMocks
    RegisterServiceImp registerServiceImp;

    Register register;
    RegisterCreateDto registerCreateDto;
    RegisterDto registerDto;

    RegisterUpdateDto registerUpdateDto;

    @BeforeEach
    void setup(){


        registerCreateDto = new RegisterCreateDto();
        registerCreateDto.setGlucose(100);
        registerCreateDto.setLocalDateTime((LocalDateTime.parse("2023-01-04T22:24:00")));
        registerCreateDto.setCarboHydrates(95);

        register = new Register();
        register.setGlucose(registerCreateDto.getGlucose());
        register.setLocalDateTime(registerCreateDto.getLocalDateTime());
        register.setCarboHydrates(registerCreateDto.getCarboHydrates());

        registerDto = new RegisterDto();
        registerDto.setGlucose(register.getGlucose());
        registerDto.setLocalDateTime(register.getLocalDateTime());
        registerDto.setCarboHydrates(register.getCarboHydrates());

        registerUpdateDto = new RegisterUpdateDto();
        registerUpdateDto.setCarboHydrates(80);
        registerUpdateDto.setGlucose(75);
    }

    @Nested
    @Tag("Register Unit tests")
    class RegisterUnitTests {


        @Test
        @DisplayName("Create a valid register and returns 200")
        public void createAValidRegister200() {

            given(registerRepository.findByLocalDateTime(registerCreateDto.getLocalDateTime())).willReturn(null);

            given(registerRepository.save(register)).willReturn(register);


            assertEquals(registerDto.getCarboHydrates(), register.getCarboHydrates());
        }

        @Test
        @DisplayName("Try to create existing register and returns 400")
        public void tryCreateAnExistingRegister400() {

            given(registerRepository.save(register)).willReturn(register);

            given(registerRepository.findByLocalDateTime(registerCreateDto.getLocalDateTime())).willThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                registerServiceImp.create(registerCreateDto, 1L);
            });
        }

        @Test
        @DisplayName("delete existing register and returns 200")
        public void deleteAnExistingRegister200() {

            given(registerRepository.save(register)).willReturn(register);

            registerRepository.delete(register);

            given(registerRepository.findByLocalDateTime(register.getLocalDateTime())).willReturn(null);

            assertEquals(null, register.getId());
        }

        @Test
        @DisplayName("try to delete and invalid register and returns 404")
        public void deleteAnExistingRegister400() {

            given(registerRepository.save(register)).willReturn(register);

            when(registerRepository.findById(2L)).thenReturn(null);

            assertNotEquals(2L, register.getId());
        }

        @Test
        @DisplayName("try to update an existing register and returns 200")
        public void updateExistingRegister200() {
            RegisterDto registerUpdated = new RegisterDto();
            registerUpdated.setId(register.getId());
            registerUpdated.setCarboHydrates(registerUpdateDto.getCarboHydrates());
            registerUpdated.setGlucose(registerUpdateDto.getGlucose());
            registerUpdated.setLocalDateTime(register.getLocalDateTime());

            given(registerRepository.save(register)).willReturn(register);

            when(registerRepository.findById(register.getId())).thenReturn(Optional.ofNullable(register));

            given(registerRepository.findByLocalDateTime(register.getLocalDateTime())).willReturn(register);

            when(registerServiceImp.update(register.getId(), registerUpdateDto)).thenReturn(registerUpdated);

            assertEquals(registerUpdateDto.getCarboHydrates(), registerUpdated.getCarboHydrates());
            assertEquals(registerUpdateDto.getGlucose(), registerUpdated.getGlucose());
            assertEquals(register.getId(), registerUpdated.getId());
        }

        @Test
        @DisplayName("try to update a register with invalid id and returns 404")
        public void tryUpdateARegisterWithInvalidId404() {

            given(registerRepository.save(register)).willReturn(register);

            when(registerRepository.findById(register.getId())).thenReturn(Optional.ofNullable(register));

            when(registerRepository.findById(2L)).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () ->{
               registerServiceImp.update(2L, registerUpdateDto);
            });

        }
    }

}