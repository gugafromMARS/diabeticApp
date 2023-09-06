package mindera.mindswap.personalproject.patient.controller;


import mindera.mindswap.personalproject.diabeticDetails.dto.DiabeticDetailsCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientCreateDto;
import mindera.mindswap.personalproject.patient.dto.PatientUpdateDto;
import mindera.mindswap.personalproject.patient.service.PatientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientServiceImp patientServiceImp;

    @Autowired
    public PatientController(PatientServiceImp patientServiceImp) {
        this.patientServiceImp = patientServiceImp;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(patientServiceImp.getAll());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getById(@PathVariable ("patientId") Long patientId){
        return ResponseEntity.ok(patientServiceImp.getById(patientId));
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<?> getAllAppointments(@PathVariable ("patientId") Long patientId){
        return ResponseEntity.ok(patientServiceImp.getAllAppointments(patientId));
    }

    @GetMapping("/{patientId}/appointments/{appointmentId}")
    public ResponseEntity<?> getAppointment(@PathVariable ("patientId") Long patientId, @PathVariable ("appointmentId") Long appointmentId){
        return ResponseEntity.ok(patientServiceImp.getAppointmentById(patientId, appointmentId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PatientCreateDto patientCreateDto){
        return new ResponseEntity<>(patientServiceImp.create(patientCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/{patientId}/diabeticDetails")
    public ResponseEntity<?> createDiabeticDetails(@PathVariable ("patientId") Long patientId, @RequestBody DiabeticDetailsCreateDto diabeticDetailsCreateDto){
        return new ResponseEntity<>(patientServiceImp.createDiabeticDetails(patientId, diabeticDetailsCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId){
        patientServiceImp.delete(patientId);
        return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{patientId}/insulin/{insulinId}")
    public ResponseEntity<String> delete(@PathVariable Long patientId,@PathVariable Long insulinId){
        patientServiceImp.deleteInsulinById(patientId, insulinId);
        return new ResponseEntity<>("Insulin deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/{patientId}")
    public ResponseEntity<?> update(@PathVariable Long patientId, @RequestBody PatientUpdateDto patientUpdateDto){
        return ResponseEntity.ok(patientServiceImp.update(patientId, patientUpdateDto));
    }
}
