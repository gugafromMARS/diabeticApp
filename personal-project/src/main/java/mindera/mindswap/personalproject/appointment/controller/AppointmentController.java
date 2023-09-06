package mindera.mindswap.personalproject.appointment.controller;


import mindera.mindswap.personalproject.appointment.dto.AppointmentCreateDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentUpdateDto;
import mindera.mindswap.personalproject.appointment.service.AppointmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentServiceImp appointmentServiceImp;

    @Autowired
    public AppointmentController(AppointmentServiceImp appointmentServiceImp) {
        this.appointmentServiceImp = appointmentServiceImp;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(appointmentServiceImp.getAll());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getById(@PathVariable ("appointmentId") Long appointmentId) {
        return ResponseEntity.ok(appointmentServiceImp.getById(appointmentId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getAllByPatientId(@PathVariable ("patientId") Long patientId){
       return ResponseEntity.ok(appointmentServiceImp.getAllByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAllByDoctorId(@PathVariable ("doctorId") Long doctorId){
        return ResponseEntity.ok(appointmentServiceImp.getAllByDoctorId(doctorId));
    }

    @PostMapping("/doctor/{doctorId}/patient/{patientId}")
    public ResponseEntity<?> create(@PathVariable ("doctorId") Long doctorId ,
                                                 @PathVariable ("patientId") Long patientId,
                                                 @RequestBody AppointmentCreateDto appointmentCreateDto){
        return new ResponseEntity<>(appointmentServiceImp.create(doctorId, patientId, appointmentCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> delete(@PathVariable ("appointmentId") Long appointmentId){
        appointmentServiceImp.delete(appointmentId);
        return new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<?> update(@PathVariable("appointmentId") Long appointmentId,
                                                 @RequestBody AppointmentUpdateDto appointmentUpdateDto){
       return ResponseEntity.ok(appointmentServiceImp.update(appointmentId, appointmentUpdateDto));
    }
}
