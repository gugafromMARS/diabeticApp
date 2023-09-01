package mindera.mindswap.personalproject.controller;


import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.dto.patient.PatientUpdateDto;
import mindera.mindswap.personalproject.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDto> getAll(){
        return patientService.getAll();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDto> getById(@PathVariable ("patientId") Long patientId){
        PatientDto patientDto = patientService.getById(patientId);
        if(patientDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientDto> create(@RequestBody PatientCreateDto patientCreateDto){
        PatientDto patientDto = patientService.create(patientCreateDto);
        if(patientDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(patientDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId){
        patientService.delete(patientId);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{patientId}/insulin/{insulinId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId,@PathVariable Long insulinId){
        patientService.deleteInsulinById(patientId, insulinId);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDto> update(@PathVariable Long patientId, @RequestBody PatientUpdateDto patientUpdateDto){
        PatientDto patientDto = patientService.update(patientId, patientUpdateDto);
        if(patientDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }
}
