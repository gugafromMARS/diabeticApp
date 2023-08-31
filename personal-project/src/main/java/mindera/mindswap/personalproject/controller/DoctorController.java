package mindera.mindswap.personalproject.controller;


import mindera.mindswap.personalproject.dto.doctor.DoctorCreateDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorDto;
import mindera.mindswap.personalproject.dto.doctor.DoctorUpdateDto;
import mindera.mindswap.personalproject.service.DoctorService;
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

}
