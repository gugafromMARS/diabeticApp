package mindera.mindswap.personalproject.patient.controller;


import mindera.mindswap.personalproject.diabeticDetails.dto.DiabeticDetailsCreateDto;
import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticDetails;
import mindera.mindswap.personalproject.patient.dto.PatientCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientDto;
import mindera.mindswap.personalproject.patient.dto.PatientUpdateDto;
import mindera.mindswap.personalproject.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getById(@PathVariable ("patientId") Long patientId){
        return ResponseEntity.ok(patientService.getById(patientId));
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<?> getAllAppointments(@PathVariable ("patientId") Long patientId){
        return ResponseEntity.ok(patientService.getAllAppointments(patientId));
    }

    @GetMapping("/{patientId}/appointments/{appointmentId}")
    public ResponseEntity<?> getAppointment(@PathVariable ("patientId") Long patientId, @PathVariable ("appointmentId") Long appointmentId){
        return ResponseEntity.ok(patientService.getAppointmentById(patientId, appointmentId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PatientCreateDto patientCreateDto){
        return new ResponseEntity<>(patientService.create(patientCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/{patientId}/diabeticDetails")
    public ResponseEntity<?> createDiabeticDetails(@PathVariable ("patientId") Long patientId, @RequestBody DiabeticDetailsCreateDto diabeticDetailsCreateDto){
        return new ResponseEntity<>(patientService.createDiabeticDetails(patientId, diabeticDetailsCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId){
        patientService.delete(patientId);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{patientId}/insulin/{insulinId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId,@PathVariable Long insulinId){
        patientService.deleteInsulinById(patientId, insulinId);
        return new ResponseEntity<>("Insulin deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/{patientId}")
    public ResponseEntity<?> update(@PathVariable Long patientId, @RequestBody PatientUpdateDto patientUpdateDto){
        return ResponseEntity.ok(patientService.update(patientId, patientUpdateDto));
    }
}
