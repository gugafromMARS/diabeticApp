package mindera.mindswap.personalproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mindera.mindswap.personalproject.converter.PatientConverter;
import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.model.patient.Patient;
import mindera.mindswap.personalproject.repository.PatientRepository;
import mindera.mindswap.personalproject.service.PatientService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PatientService patientService;

    @Autowired
    private  ObjectMapper objectMapper;


    PatientCreateDto patientCreateDto = new PatientCreateDto();
    PatientDto patientDto = new PatientDto();


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

    @Nested
    @Tag("Controller tests")
    public class ControllerTests {
        @Test
        @DisplayName("Create a patient and return 200 with patientDto")
        public void createAPatientReturn200() throws Exception {
            // given
            given(patientService.create(any(PatientCreateDto.class)))
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
        @DisplayName("Give an invalid id and return 404")
        public void giveAnInvalidPatientId404() throws Exception {
            Long id = 53L;

            when(patientService.getById(id)).thenReturn(null);

            ResultActions response = mockMvc.perform(get("/patients/{patientId}", id));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }
    }



}