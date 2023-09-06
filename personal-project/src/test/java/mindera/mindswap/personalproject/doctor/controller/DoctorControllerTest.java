package mindera.mindswap.personalproject.doctor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.appointment.model.Appointment;
import mindera.mindswap.personalproject.appointment.repository.AppointmentRepository;
import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorUpdateDto;
import mindera.mindswap.personalproject.doctor.model.Doctor;
import mindera.mindswap.personalproject.doctor.repository.DoctorRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    ObjectMapper objectMapper;

    DoctorCreateDto doctorCreateDto = new DoctorCreateDto();
    DoctorDto doctorDto = new DoctorDto();
    DoctorUpdateDto doctorUpdateDto = new DoctorUpdateDto();
    Appointment appointment = new Appointment();
    List<Appointment> appointments = List.of(appointment);

    @BeforeEach
    public void setUp(){
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();

        doctorCreateDto.setName("Marco");
        doctorCreateDto.setEmail("marco@gmail.com");


        doctorDto.setName(doctorCreateDto.getName());
        doctorDto.setEmail(doctorCreateDto.getEmail());
    }

    public Doctor persistDoctor() {
        Doctor doctor = Doctor.builder()
                .name(doctorCreateDto.getName())
                .email(doctorCreateDto.getEmail())
                .address("Rua da liberdade")
                .appointments(appointments)
                .build();
        appointment.setLocalDate(LocalDate.now());
        doctorRepository.save(doctor);
        return doctor;
    }

    public void updatedDoctor(){
        doctorUpdateDto.setEmail(doctorCreateDto.getEmail());
        doctorUpdateDto.setHabitation("Rua principal");
    }

    @Nested
    @Tag("Integration tests")
    class DoctorControllerTests{
        @Test
        @DisplayName("Create a valid doctor and return 200")
        public void createAValidDoctorReturn200() throws Exception {

            ResultActions response = mockMvc.perform(post("/doctor")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctorCreateDto)));


            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name",is(doctorCreateDto.getName())))
                    .andExpect(jsonPath("$.email", is(doctorCreateDto.getEmail())));

        }

        @Test
        @DisplayName("Try to create an invalid doctor and return 400")
        public void tryToCreateAnInvalidDoctor400() throws Exception {
            Doctor doctor = persistDoctor();

            ResultActions response = mockMvc.perform(post("/doctor")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctorCreateDto)));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Get appointments by a valid id and return 200")
        public void getAppointmentByValidId200() throws Exception {
            Doctor doctor = persistDoctor();

            ResultActions response = mockMvc.perform(get("/doctor/{doctorId}/appointments", doctor.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Get appointments by an invalid id and return 404")
        public void getAppointmentByInValidId404() throws Exception {

            ResultActions response = mockMvc.perform(get("/doctor/{doctorId}/appointments", 1L));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Delete a valid doctor and return 200")
        public void deleteAValidDoctor200() throws Exception {
            Doctor doctor = persistDoctor();

            ResultActions response = mockMvc.perform(delete("/doctor/{doctorId}", doctor.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Delete an invalid doctor and return 404")
        public void deleteAnInvalidDoctor404() throws Exception {

            ResultActions response = mockMvc.perform(delete("/doctor/{doctorId}", 1L));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Update a valid doctor and return 200")
        public void updateAValidDoctor200() throws Exception {
            Doctor doctor = persistDoctor();
            updatedDoctor();

            ResultActions response = mockMvc.perform(put("/doctor/{doctorId}", doctor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctorUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

        }

        @Test
        @DisplayName("Update an  invalid doctor and return 404")
        public void updateAnInValidDoctor404() throws Exception {
            updatedDoctor();

            ResultActions response = mockMvc.perform(put("/doctor/{doctorId}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctorUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());

        }



    }





}