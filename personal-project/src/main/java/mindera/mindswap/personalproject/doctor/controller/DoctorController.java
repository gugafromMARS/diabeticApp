package mindera.mindswap.personalproject.doctor.controller;


import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorUpdateDto;
import mindera.mindswap.personalproject.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getById(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.getById(doctorId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DoctorCreateDto doctorCreateDto){
        return new ResponseEntity<>(doctorService.create(doctorCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> delete(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorService.delete(doctorId));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<?> update(@PathVariable Long doctorId, @RequestBody DoctorUpdateDto doctorUpdateDto){
        return ResponseEntity.ok(doctorService.update(doctorId, doctorUpdateDto));
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<?> getAllAppointments(@PathVariable ("doctorId") Long doctorId){
        return ResponseEntity.ok(doctorService.getAppointments(doctorId));
    }

    @GetMapping("/{doctorId}/appointments/{appointmentId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable ("doctorId") Long doctorId, @PathVariable ("appointmentId") Long appointmentId){
        return ResponseEntity.ok(doctorService.getAppointmentById(doctorId, appointmentId));
    }

}
