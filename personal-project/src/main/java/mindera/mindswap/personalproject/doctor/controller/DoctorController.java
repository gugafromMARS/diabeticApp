package mindera.mindswap.personalproject.doctor.controller;


import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorUpdateDto;
import mindera.mindswap.personalproject.doctor.service.DoctorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    DoctorServiceImp doctorServiceImp;

    @Autowired
    public DoctorController(DoctorServiceImp doctorServiceImp) {
        this.doctorServiceImp = doctorServiceImp;
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getById(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorServiceImp.getById(doctorId));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DoctorCreateDto doctorCreateDto){
        return new ResponseEntity<>(doctorServiceImp.create(doctorCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> delete(@PathVariable Long doctorId){
        return ResponseEntity.ok(doctorServiceImp.delete(doctorId));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<?> update(@PathVariable Long doctorId, @RequestBody DoctorUpdateDto doctorUpdateDto){
        return ResponseEntity.ok(doctorServiceImp.update(doctorId, doctorUpdateDto));
    }

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<?> getAllAppointments(@PathVariable ("doctorId") Long doctorId){
        return ResponseEntity.ok(doctorServiceImp.getAppointments(doctorId));
    }

    @GetMapping("/{doctorId}/appointments/{appointmentId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable ("doctorId") Long doctorId, @PathVariable ("appointmentId") Long appointmentId){
        return ResponseEntity.ok(doctorServiceImp.getAppointmentById(doctorId, appointmentId));
    }

}
