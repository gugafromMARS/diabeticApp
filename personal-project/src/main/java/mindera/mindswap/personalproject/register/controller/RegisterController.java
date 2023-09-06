package mindera.mindswap.personalproject.register.controller;


import mindera.mindswap.personalproject.register.dto.RegisterCreateDto;
import mindera.mindswap.personalproject.register.dto.RegisterDto;
import mindera.mindswap.personalproject.register.dto.RegisterUpdateDto;
import mindera.mindswap.personalproject.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registers")
public class RegisterController {


    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(registerService.getAll());
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<?> create(@RequestBody RegisterCreateDto registerCreateDto, @PathVariable ("patientId") Long patientId){
        return new ResponseEntity<>(registerService.create(registerCreateDto, patientId), HttpStatus.CREATED);
    }


    @DeleteMapping("/{registerId}")
    public ResponseEntity<String> deleteById(@PathVariable ("registerId") Long registerId){
        registerService.deleteById(registerId);
        return new ResponseEntity<>("Register deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{registerId}")
    public ResponseEntity<?> update(@PathVariable ("registerId") Long registerId,
                                              @RequestBody RegisterUpdateDto registerUpdateDto) {
        return new ResponseEntity<>(registerService.update(registerId, registerUpdateDto), HttpStatus.OK);
    }


}
