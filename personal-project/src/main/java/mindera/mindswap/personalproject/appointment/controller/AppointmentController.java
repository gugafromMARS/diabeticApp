package mindera.mindswap.personalproject.appointment.controller;


import mindera.mindswap.personalproject.appointment.dto.AppointmentCreateDto;
import mindera.mindswap.personalproject.appointment.dto.AppointmentDto;
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
    public ResponseEntity<List<AppointmentDto>> getAll(@PathVariable ("doctorId") Long doctorId){
        List<AppointmentDto> appointmentDtos = appointmentService.getAll(doctorId);
        if(appointmentDtos == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(appointmentDtos, HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}/doctor/{doctorId}")
    public ResponseEntity<AppointmentDto> getById(@PathVariable ("doctorId") Long doctorId,
                                                  @PathVariable ("appointmentId") Long appointmentId) {
        AppointmentDto appointmentDto = appointmentService.getById(doctorId, appointmentId);
        if(appointmentDto == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(appointmentDto, HttpStatus.OK);
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



}
