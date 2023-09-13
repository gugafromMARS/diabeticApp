package mindera.mindswap.personalproject.service.doctor;

import mindera.mindswap.personalproject.converter.appointment.AppointmentConverter;
import mindera.mindswap.personalproject.converter.doctor.DoctorConverter;
import mindera.mindswap.personalproject.dto.doctor.DoctorCreateDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorUpdateDto;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.repository.doctor.DoctorRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;



@ExtendWith(SpringExtension.class)
class DoctorServiceImpTest {

    @Mock
    DoctorRepository doctorRepository;

    @Mock
    DoctorConverter doctorConverter;

    @Mock
    AppointmentConverter appointmentConverter;

    @InjectMocks
    DoctorServiceImp doctorServiceImp;

    Doctor doctor;
    DoctorDto doctorDto;
    DoctorCreateDto doctorCreateDto;
    DoctorUpdateDto doctorUpdateDto;

    @BeforeEach
    void setup(){
        doctor = Doctor.builder()
                .id(1L)
                .email("gugaloko@gmail.com")
                .build();

        doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setEmail(doctor.getEmail());

        doctorCreateDto = new DoctorCreateDto();
        doctorCreateDto.setEmail(doctor.getEmail());

        doctorUpdateDto = new DoctorUpdateDto();
        doctorUpdateDto.setEmail("guga2@gmail.com");
    }


    @Nested
    @Tag("Service Unit Tests")
    public class DoctorServiceUnitTests{
        @Test
        @DisplayName("get a doctor with a valid id and return 200")
        public void getDoctorFromValidId200(){

            given(doctorRepository.findById(doctor.getId())).willReturn(Optional.of(doctor));

            assertEquals(1L, doctorDto.getId());
        }

        @Test
        @DisplayName("get a doctor with an invalid id and return 404")
        public void getDoctorFromInValidId404(){

            given(doctorRepository.findById(2L)).willReturn(Optional.empty());
        }

        @Test
        @DisplayName("create a valid doctor and return 200")
        public void createValidDoctor200(){

            given(doctorRepository.findByEmail(doctor.getEmail())).willReturn(null);

            given(doctorRepository.save(doctor)).willReturn(doctor);

            assertEquals(doctorDto.getId(), doctor.getId());
        }

        @Test
        @DisplayName("Try to create a exists doctor and return 400")
        public void createDoctorWithValidId200(){

            given(doctorRepository.save(doctor)).willReturn(doctor);

            given(doctorRepository.findByEmail(doctorCreateDto.getEmail())).willThrow(ResponseStatusException.class);

            Assertions.assertThrows(ResponseStatusException.class, () -> {
                doctorServiceImp.create(doctorCreateDto);
            });
        }


        @Test
        @DisplayName("Delete a doctor with valid id and returns 200")
        public void deleteADoctorWithValidId200(){
            Doctor newDoctor = new Doctor();
            given(doctorRepository.save(newDoctor)).willReturn(newDoctor);

            given(doctorRepository.findById(newDoctor.getId())).willReturn(Optional.of(newDoctor));

            doctorRepository.delete(newDoctor);

            given(doctorRepository.findById(newDoctor.getId())).willReturn(null);

            assertEquals(null, newDoctor.getId());
        }

        @Test
        @DisplayName("Delete a doctor with invalid id and returns 404")
        public void deleteADoctorWithInValidId404(){
            Doctor newDoctor = new Doctor();
            given(doctorRepository.save(newDoctor)).willReturn(newDoctor);

            given(doctorRepository.findById(2L)).willReturn(null);

            assertNotEquals(2L, newDoctor.getId());
        }

        @Test
        @DisplayName("Update a doctor with valid id an returns 200")
        public void updateDoctorWithValidId200(){

            given(doctorRepository.findById(doctor.getId())).willReturn(Optional.ofNullable(doctor));

            DoctorDto updatedDoctor = new DoctorDto();
            updatedDoctor.setEmail(doctorUpdateDto.getEmail());
            updatedDoctor.setId(doctor.getId());

            given(doctorServiceImp.update(doctor.getId(), doctorUpdateDto)).willReturn(updatedDoctor);

            assertEquals("guga2@gmail.com", doctor.getEmail());

        }

        @Test
        @DisplayName("Update a doctor with invalid id an returns 404")
        public void updateDoctorWithValidId404(){


            DoctorDto updatedDoctor = new DoctorDto();
            updatedDoctor.setEmail(doctorUpdateDto.getEmail());
            updatedDoctor.setId(doctor.getId());

            given(doctorRepository.findById(doctor.getId())).willThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () ->{
                doctorServiceImp.update(doctor.getId(), doctorUpdateDto);
            });

        }

    }
}



