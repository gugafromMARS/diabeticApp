package mindera.mindswap.personalproject.appointment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.appointment.dto.AppointmentCreateDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentUpdateDto;
import mindera.mindswap.personalproject.appointment.model.Appointment;
import mindera.mindswap.personalproject.appointment.repository.AppointmentRepository;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.doctor.repository.DoctorRepository;
import mindera.mindswap.personalproject.patient.model.Patient;
import mindera.mindswap.personalproject.patient.repository.PatientRepository;
import mindera.mindswap.personalproject.register.model.Register;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class AppointmentControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ObjectMapper objectMapper;

    AppointmentCreateDto appointmentCreateDto = new AppointmentCreateDto();

    AppointmentUpdateDto appointmentUpdateDto = new AppointmentUpdateDto();
    Doctor doctor = new Doctor();
    Patient patient = new Patient();

    @BeforeEach
    public void setUp(){
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        patientRepository.deleteAll();
    }

    public void persistDoctorAndPatient(){
        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }

    public Appointment persistsAppointment(){
        doctorRepository.save(doctor);
        patientRepository.save(patient);
        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .localDate(LocalDate.now())
                .build();
        appointmentRepository.save(appointment);
        appointmentUpdateDto.setLocalDate(LocalDate.of(2023,7,2));
        return appointment;
    }
    @Nested
    @Tag("CRUD tests")
    public class AppointmentIntegrationTests{

        @Test
        @DisplayName("Create a valid appointment and return 200")
        public void createAValidAppointment200() throws Exception {
            persistDoctorAndPatient();

            ResultActions response = mockMvc.perform(post("/appointments/doctor/{doctorId}/patient/{patientId}",
                    doctor.getId(), patient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(appointmentCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Create an invalid appointment and return 404")
        public void createAnInvalidAppointment404() throws Exception {
            persistDoctorAndPatient();

            ResultActions response = mockMvc.perform(post("/appointments/doctor/{doctorId}/patient/{patientId}",
                    2L, patient.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(appointmentCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Get a valid appointment by id and return 200")
        public void getValidAppointmentById() throws Exception {
            persistDoctorAndPatient();
            Appointment appointment = persistsAppointment();

            ResultActions response = mockMvc.perform(get("/appointments/{appointmentId}" , appointment.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

        }

        @Test
        @DisplayName("Try to get an appointment by an invalid id and return 404")
        public void tryToGetAppointmentByInvalidId404() throws Exception {
            persistDoctorAndPatient();
            Appointment appointment = persistsAppointment();

            ResultActions response = mockMvc.perform(get("/appointments/{appointmentId}" , 2L));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());

        }

        @Test
        @DisplayName("Delete an appointment by a valid id return 200")
        public void deleteAnAppointmentByValidId200() throws Exception {
            persistDoctorAndPatient();
            Appointment appointment = persistsAppointment();

            ResultActions response = mockMvc.perform(delete("/appointments/{appointmentId}", appointment.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

        }

        @Test
        @DisplayName("Try to delete an appointment by an invalid id return 404")
        public void tryToDeleteAnAppointmentByInValidId404() throws Exception {
            persistDoctorAndPatient();
            Appointment appointment = persistsAppointment();

            ResultActions response = mockMvc.perform(delete("/appointments/{appointmentId}", 2L));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());

        }

        @Test
        @DisplayName("Update a valid appointment and return 200")
        public void updateValidAppointment200() throws Exception {
            persistDoctorAndPatient();
            Appointment appointment = persistsAppointment();

            ResultActions response = mockMvc.perform(put("/appointments/{appointmentId}", appointment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(appointmentUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

        }

        @Test
        @DisplayName("Try to update an appointment with invalid id and return 404")
        public void updateInvalidAppointment404() throws Exception {
            persistDoctorAndPatient();
            Appointment appointment = persistsAppointment();

            ResultActions response = mockMvc.perform(put("/appointments/{appointmentId}", 2L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(appointmentUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

    }
}