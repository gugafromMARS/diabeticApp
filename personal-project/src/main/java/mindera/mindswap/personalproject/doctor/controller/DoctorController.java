package mindera.mindswap.personalproject.doctor.controller;


import mindera.mindswap.personalproject.doctor.dto.DoctorCreateDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorDto;
import mindera.mindswap.personalproject.doctor.dto.DoctorUpdateDto;
import mindera.mindswap.personalproject.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DoctorDto getById(@PathVariable Long doctorId){
        return doctorService.getById(doctorId);
    }

    @PostMapping
    public DoctorDto create(@RequestBody DoctorCreateDto doctorCreateDto){
        return doctorService.create(doctorCreateDto);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> delete(@PathVariable Long doctorId){
        return doctorService.delete(doctorId);
    }

    @PutMapping("/{doctorId}")
    public DoctorDto update(@PathVariable Long doctorId, @RequestBody DoctorUpdateDto doctorUpdateDto){
        return doctorService.update(doctorId, doctorUpdateDto);
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
