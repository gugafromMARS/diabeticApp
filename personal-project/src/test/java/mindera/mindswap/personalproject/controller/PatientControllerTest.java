package mindera.mindswap.personalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.patient.dto.PatientCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientDto;
import mindera.mindswap.personalproject.patient.dto.PatientUpdateDto;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticDetails;
import mindera.mindswap.personalproject.patient.controller.PatientController;
import mindera.mindswap.personalproject.patient.service.PatientServiceImp;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PatientServiceImp patientServiceImp;

    @Autowired
    private  ObjectMapper objectMapper;

    PatientCreateDto patientCreateDto = new PatientCreateDto();
    PatientDto patientDto = new PatientDto();
    PatientUpdateDto patientUpdateDto = new PatientUpdateDto();
    PatientDto DtoUpdated = new PatientDto();
    @BeforeEach
    void createPatient(){
        //patientCreateDto setup
        patientCreateDto.setName("Alberto");
        patientCreateDto.setEmail("albertinho@gmail.com");
        patientCreateDto.setAge(40);


        //patientDto setup
        patientDto.setId(1L);
        patientDto.setName(patientCreateDto.getName());
        patientDto.setEmail(patientCreateDto.getEmail());
        patientDto.setAge(patientCreateDto.getAge());
    }

    public void updatePatient(){
        patientUpdateDto.setInsulinPerCarbohydrate(4);

        DiabeticDetails details = new DiabeticDetails();

        DtoUpdated.setName(patientDto.getName());
        DtoUpdated.setEmail(patientDto.getEmail());
        DtoUpdated.setAge(patientDto.getAge());
        DtoUpdated.setId(patientDto.getId());

    }

    @Nested
    @Tag("Controller tests")
    public class ControllerTests {
        @Test
        @DisplayName("Create a patient and return 200 with patientDto")
        public void createAPatientReturn200() throws Exception {
            // given
            given(patientServiceImp.create(any(PatientCreateDto.class)))
                    .willReturn(patientDto);

            // when
            ResultActions response = mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(patientCreateDto)));
            // then
            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is(patientDto.getName())))
                    .andExpect(jsonPath("$.email", is(patientDto.getEmail())))
                    .andExpect(jsonPath("$.age", is(patientDto.getAge())));
        }
        @Test
        @DisplayName("Create an exists patient and return 400")
        public void createExistsPatient400() throws Exception {

            when(patientServiceImp.create(patientCreateDto)).thenReturn(null);

            ResultActions response = mockMvc.perform(post("/patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(patientCreateDto)));

                response.andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Give a valid id and return 200")
        public void giveAValidPatientId200() throws Exception {

            Long id = 1L;

            given(patientServiceImp.getById(id)).willReturn(patientDto);

            ResultActions response = mockMvc.perform(get("/patients/{patientId}", id));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Give an invalid id and return 404")
        public void giveAnInvalidPatientId404() throws Exception {
            Long id = 53L;

            when(patientServiceImp.getById(id)).thenReturn(null);

            ResultActions response = mockMvc.perform(get("/patients/{patientId}", id));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Delete a valid patient and return 200")
        public void deleteAValidPatient200() throws Exception {
            Long id = 1L;

            willDoNothing().given(patientServiceImp).delete(id);

            ResultActions response = mockMvc.perform(delete("/patients/{patientId}", id));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Update a valid patient and returns 200")
        public void updateValidPatient200() throws Exception {
            updatePatient();
            Long id = patientDto.getId();

            given(patientServiceImp.getById(id)).willReturn(patientDto);
            given(patientServiceImp.update(any(Long.class),any(PatientUpdateDto.class))).willReturn(DtoUpdated);

            ResultActions response = mockMvc.perform(put("/patients/{patientId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(patientUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(DtoUpdated.getName())))
                    .andExpect(jsonPath("$.email", is(DtoUpdated.getEmail())))
                    .andExpect(jsonPath("$.age", is(DtoUpdated.getAge())));
        }

        @Test
        @DisplayName("Try to update with a invalid patient id and returns 400")
        public void updateInValidPatient400() throws Exception {
            updatePatient();
            Long id = patientDto.getId();

            given(patientServiceImp.getById(id)).willReturn(patientDto);
            given(patientServiceImp.update(id,patientUpdateDto)).willReturn(DtoUpdated);

            ResultActions response = mockMvc.perform(put("/patients/{patientId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(patientUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }




    }



}