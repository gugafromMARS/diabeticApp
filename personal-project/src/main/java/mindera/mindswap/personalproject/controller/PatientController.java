package mindera.mindswap.personalproject.controller;


import mindera.mindswap.personalproject.dto.patient.PatientCreateDto;
import mindera.mindswap.personalproject.dto.patient.PatientDto;
import mindera.mindswap.personalproject.dto.patient.PatientUpdateDto;
import mindera.mindswap.personalproject.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(patientDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto create(@RequestBody PatientCreateDto patientCreateDto){
        return patientService.create(patientCreateDto);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId){
        return patientService.delete(patientId);
    }
    @DeleteMapping("/{patientId}/insulin/{insulinId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId,@PathVariable Long insulinId){
        return patientService.deleteInsulinById(patientId, insulinId);
    }


    @PutMapping("/{patientId}")
    public PatientDto update(@PathVariable Long patientId, @RequestBody PatientUpdateDto patientUpdateDto){
        return patientService.update(patientId, patientUpdateDto);
    }
}
