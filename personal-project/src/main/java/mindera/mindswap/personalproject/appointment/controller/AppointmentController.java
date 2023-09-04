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
    public ResponseEntity<List<AppointmentDto>> getAll(){
        List<AppointmentDto> appointmentDtos = appointmentService.getAll();
        if(appointmentDtos == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(appointmentDtos, HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> getById(@PathVariable ("appointmentId") Long appointmentId) {
        AppointmentDto appointmentDto = appointmentService.getById(appointmentId);
        if(appointmentDto == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(appointmentDto, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getAllByPatientId(@PathVariable ("patientId") Long patientId){
        List<AppointmentDto> appointmentDtos = appointmentService.getAllByPatientId(patientId);
        if(appointmentDtos == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(appointmentDtos, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentDto>> getAllByDoctorId(@PathVariable ("doctorId") Long doctorId){
        List<AppointmentDto> appointmentDtos = appointmentService.getAllByDoctorId(doctorId);
        if(appointmentDtos == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(appointmentDtos, HttpStatus.OK);
    }

    @PostMapping("/doctor/{doctorId}/patient/{patientId}")
    public ResponseEntity<AppointmentDto> create(@PathVariable ("doctorId") Long doctorId ,
                                                 @PathVariable ("patientId") Long patientId,
                                                 @RequestBody AppointmentCreateDto appointmentCreateDto){
        AppointmentDto appointmentDto = appointmentService.create(doctorId, patientId, appointmentCreateDto);
        if(appointmentDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(appointmentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> delete(@PathVariable ("appointmentId") Long appointmentId){
        appointmentService.delete(appointmentId);
        return new ResponseEntity<>("Appointment deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> update(@PathVariable("appointmentId") Long appointmentId,
                                                 @RequestBody AppointmentUpdateDto appointmentUpdateDto){
        AppointmentDto appointmentDto = appointmentService.update(appointmentId, appointmentUpdateDto);
        if(appointmentDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(appointmentDto, HttpStatus.OK);
    }
}
