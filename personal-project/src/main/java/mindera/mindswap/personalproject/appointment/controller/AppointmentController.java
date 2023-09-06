package mindera.mindswap.personalproject.appointment.controller;


import mindera.mindswap.personalproject.appointment.dto.AppointmentCreateDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentUpdateDto;
import mindera.mindswap.personalproject.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getById(@PathVariable ("appointmentId") Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getById(appointmentId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getAllByPatientId(@PathVariable ("patientId") Long patientId){
       return ResponseEntity.ok(appointmentService.getAllByPatientId(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getAllByDoctorId(@PathVariable ("doctorId") Long doctorId){
        return ResponseEntity.ok(appointmentService.getAllByDoctorId(doctorId));
    }

    @PostMapping("/doctor/{doctorId}/patient/{patientId}")
    public ResponseEntity<AppointmentDto> create(@PathVariable ("doctorId") Long doctorId ,
                                                 @PathVariable ("patientId") Long patientId,
                                                 @RequestBody AppointmentCreateDto appointmentCreateDto){
        return new ResponseEntity<>(appointmentService.create(doctorId, patientId, appointmentCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> delete(@PathVariable ("appointmentId") Long appointmentId){
        appointmentService.delete(appointmentId);
        return new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> update(@PathVariable("appointmentId") Long appointmentId,
                                                 @RequestBody AppointmentUpdateDto appointmentUpdateDto){
       return ResponseEntity.ok(appointmentService.update(appointmentId, appointmentUpdateDto));
    }
}
