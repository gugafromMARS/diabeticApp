package mindera.mindswap.personalproject.register.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.repository.patient.PatientRepository;
import mindera.mindswap.personalproject.dto.register.RegisterCreateDto;
import mindera.mindswap.personalproject.dto.register.RegisterUpdateDto;
import mindera.mindswap.personalproject.model.register.Register;
import mindera.mindswap.personalproject.repository.register.RegisterRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class RegisterControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    ObjectMapper objectMapper;

    RegisterCreateDto registerCreateDto = new RegisterCreateDto();
    Patient patient = new Patient();
    Register register = new Register();
    RegisterUpdateDto registerUpdateDto = new RegisterUpdateDto();


    @BeforeEach
    public void setUp() {
        registerRepository.deleteAll();
        patientRepository.deleteAll();
    }


    public Patient persistPatient(){
        DiabeticDetails details = new DiabeticDetails();
        patient.setDiabeticDetails(details);
        patientRepository.save(patient);
        return patient;
    }

    public Register persistRegister(){
        registerRepository.save(register);
        return register;
    }


    @Nested
    @Tag("Crud tests")
    class RegisterIntegrationTests{

        @Test
        @DisplayName("Create a valid register and return 200")
        public void createAValidRegister200() throws Exception {
            Patient patient1 = persistPatient();

            ResultActions response = mockMvc.perform(post("/registers/patient/{patientId}", patient1.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerCreateDto)));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Try to create register with invalid patient id and return 404")
        public void tryToCreateRegisterWithInvalidId404() throws Exception {

            ResultActions response = mockMvc.perform(post("/registers/patient/{patientId}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Delete a register with valid id return 200")
        public void deleteARegisterWithValidId200() throws Exception {
            Register register1 = persistRegister();

            ResultActions response = mockMvc.perform(delete("/registers/{registerId}", register1.getId()));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to delete a register with an invalid id return 404")
        public void tryToDeleteRegisterWithInvalidId404() throws Exception {

            ResultActions response = mockMvc.perform(delete("/registers/{registerId}", 1L));
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Update a valid register and return 200")
        public void UpdateAValidRegister200() throws Exception {
            Register register1 = persistRegister();
            registerUpdateDto.setGlucose(70);

            ResultActions response = mockMvc.perform(put("/registers/{registerId}", register1.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to update an invalid register and return 400")
        public void tryToUpdateAnInValidRegister400() throws Exception {
            Register register1 = persistRegister();

            ResultActions response = mockMvc.perform(put("/registers/{registerId}", register1.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }

    }
}