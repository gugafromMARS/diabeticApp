package mindera.mindswap.personalproject.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.dto.patient.PatientUpdateDto;
import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.repository.PatientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class PatientControllerITests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    PatientCreateDto patientCreateDto = new PatientCreateDto();
    PatientDto patientDto = new PatientDto();
    PatientUpdateDto patientUpdateDto = new PatientUpdateDto();
    PatientDto DtoUpdated = new PatientDto();
    @BeforeEach
    void setUp(){
        patientRepository.deleteAll();

        patientCreateDto.setName("Alberto");
        patientCreateDto.setEmail("albertinho@gmail.com");
        patientCreateDto.setAge(40);

        //patientDto setup
        patientDto.setName(patientCreateDto.getName());
        patientDto.setEmail(patientCreateDto.getEmail());
        patientDto.setAge(patientCreateDto.getAge());
    }

    private Patient persistPatient(){
        Patient patient = Patient.builder()
                .withEmail(patientCreateDto.getEmail())
                .withDiabeticDetails(new DiabeticDetails())
                        .build();
        patientRepository.save(patient);
        return patient;
    }
    public void updatePatient() {
        patientUpdateDto.setInsulinPerCarbohydrate(4);

        DiabeticDetails details = new DiabeticDetails();
        patientDto.setDiabeticDetails(details);
        DtoUpdated.setName(patientDto.getName());
        DtoUpdated.setEmail(patientDto.getEmail());
        DtoUpdated.setAge(patientDto.getAge());
        DtoUpdated.setId(patientDto.getId());
        DtoUpdated.setDiabeticDetails(details);
        DtoUpdated.getDiabeticDetails().setInsulinPerCarbohydrate(patientUpdateDto.getInsulinPerCarbohydrate());
    }
            @Nested
            @Tag("Controller Integration tests")
            public class ControllerIntegrationTests {
                @Test
                @DisplayName("Create a patient and return 200 with patientDto")
                public void createAPatientReturn200() throws Exception {

                    // when
                    ResultActions response = mockMvc.perform(post("/patients")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientCreateDto)));
                    // then
                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isCreated())
                            .andExpect(jsonPath("$.name", is(patientDto.getName())))
                            .andExpect(jsonPath("$.email", is(patientDto.getEmail())))
                            .andExpect(jsonPath("$.age", is(patientDto.getAge())));
                }

                @Test
                @DisplayName("Create an exists patient and return 400")
                public void createExistsPatient400() throws Exception {
                    Patient patient = persistPatient();

                    ResultActions response = mockMvc.perform(post("/patients")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientCreateDto)));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isBadRequest());
                }

                @Test
                @DisplayName("Give a valid id and return 200")
                public void giveAValidPatientId200() throws Exception {
                    Patient patient = persistPatient();

                    ResultActions response = mockMvc.perform(get("/patients/{patientId}", patient.getId()));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isOk());
                }

                @Test
                @DisplayName("Give an invalid id and return 404")
                public void giveAnInvalidPatientId404() throws Exception {
                    Patient patient = persistPatient();


                    ResultActions response = mockMvc.perform(get("/patients/{patientId}", 54));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isNotFound());
                }

                @Test
                @DisplayName("Delete a valid patient and return 200")
                public void deleteAValidPatient200() throws Exception {
                    Patient patient = persistPatient();


                    ResultActions response = mockMvc.perform(delete("/patients/{patientId}", patient.getId()));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isOk());
                }

                @Test
                @DisplayName("Try to delete a patient with invalid id and return 404")
                public void deleteAValidPatient404() throws Exception {
                    Patient patient = persistPatient();


                    ResultActions response = mockMvc.perform(delete("/patients/{patientId}", 48));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isNotFound());
                }


                @Test
                @DisplayName("Update a valid patient and returns 200")
                public void updateValidPatient200() throws Exception {
                    Patient patient = persistPatient();
                    updatePatient();

                    ResultActions response = mockMvc.perform(put("/patients/{patientId}", patient.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientUpdateDto)));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name", is(patient.getName())))
                            .andExpect(jsonPath("$.email", is(patient.getEmail())))
                            .andExpect(jsonPath("$.age", is(patient.getAge())));
                }

                @Test
                @DisplayName("Try to update with a invalid patient id and returns 404")
                public void updateInValidPatient400() throws Exception {
                    Patient patient = persistPatient();


                    ResultActions response = mockMvc.perform(put("/patients/{patientId}", 87)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(patientUpdateDto)));

                    response.andDo(MockMvcResultHandlers.print())
                            .andExpect(status().isNotFound());
                }
            }
        }
