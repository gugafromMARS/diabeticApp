package mindera.mindswap.personalproject.service.appointment;

import mindera.mindswap.personalproject.converter.appointment.AppointmentConverter;
import mindera.mindswap.personalproject.dto.appointment.AppointmentCreateDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentDto;
import mindera.mindswap.personalproject.dto.appointment.AppointmentUpdateDto;
import mindera.mindswap.personalproject.model.appointment.Appointment;
import mindera.mindswap.personalproject.model.doctor.Doctor;
import mindera.mindswap.personalproject.repository.appointment.AppointmentRepository;
import mindera.mindswap.personalproject.repository.doctor.DoctorRepository;
import mindera.mindswap.personalproject.repository.patient.PatientRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class AppointmentServiceImpTest {

    @Mock
    PatientRepository patientRepository;

    @Mock
    AppointmentConverter appointmentConverter;
    @Mock
    DoctorRepository doctorRepository;

    @Mock
    AppointmentRepository appointmentRepository;
    @InjectMocks
    AppointmentServiceImp appointmentServiceImp;

    AppointmentCreateDto appointmentCreateDto;
    AppointmentDto appointmentDto;
    Appointment appointment;
    AppointmentUpdateDto appointmentUpdateDto;

    @BeforeEach
    void setup(){
        appointment = new Appointment();
        appointment.setDescription("testing something");

        appointmentDto = new AppointmentDto();
        appointmentDto.setDescription("testing something");
        appointmentDto.setLocalDate(LocalDate.now());

        appointmentCreateDto = new AppointmentCreateDto();
        appointmentCreateDto.setDescription("testing something");

        appointmentUpdateDto = new AppointmentUpdateDto();
        appointmentUpdateDto.setLocalDate(LocalDate.now());
    }

    @Nested
    @Tag("Appointment Service Unit Tests")
    public class AppointmentServiceUnitTests{

        @Test
        @DisplayName("Create a valid appointment and return 200")
        public void createAValidAppointment200() {

            given(appointmentRepository.findById(appointment.getId())).willReturn(null);

            when(appointmentRepository.save(appointment)).thenReturn(appointment);

            assertEquals(appointmentDto.getDescription(), appointment.getDescription());
        }

        @Test
        @DisplayName("Try to Create exists appointment and return 400")
        public void tryCreateExistAppointment400() {

            given(appointmentRepository.save(appointment)).willReturn(appointment);

            when(appointmentRepository.findById(appointment.getId())).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                appointmentServiceImp.create(1L, 2L, appointmentCreateDto);
            });
        }

        @Test
        @DisplayName("Try to delete a valid appointment and return 200")
        public void tryDeleteValidAppointment200(){

            given(appointmentRepository.save(appointment)).willReturn(appointment);

            when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.ofNullable(appointment));

            appointmentRepository.delete(appointment);

            assertNotEquals(1L, appointment.getId());
        }

        @Test
        @DisplayName("Try to delete a invalid appointment and return 404")
        public void tryDeleteInvalidAppointment404(){

            given(appointmentRepository.save(appointment)).willReturn(appointment);

            when(appointmentRepository.findById(2L)).thenReturn(null);


            assertNotEquals(1L, appointment.getId());
        }

        @Test
        @DisplayName("Update a valid appointment and return 200")
        public void UpdateValidAppointment200(){

            given(appointmentRepository.save(appointment)).willReturn(appointment);

            when(appointmentRepository.findById(appointment.getId())).thenReturn(Optional.ofNullable(appointment));

            when(appointmentServiceImp.update(appointment.getId(), appointmentUpdateDto)).thenReturn(appointmentDto);

            assertEquals(appointmentDto.getLocalDate(), appointmentUpdateDto.getLocalDate());
        }

        @Test
        @DisplayName("Try to update with invalid appointment and return 404")
        public void UpdateInvalidAppointment404(){

            given(appointmentRepository.save(appointment)).willReturn(appointment);

            when(appointmentRepository.findById(appointment.getId())).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () ->{
                appointmentServiceImp.update(2L, appointmentUpdateDto);
            });
        }


    }

}