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
    public ResponseEntity<List<RegisterDto>> getAll(){
        List<RegisterDto> registerDtos = registerService.getAll();
        if(registerDtos == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(registerDtos, HttpStatus.OK);
    }


    @PostMapping("/patient/{patientId}")
    public ResponseEntity<RegisterDto> create(@RequestBody RegisterCreateDto registerCreateDto, @PathVariable ("patientId") Long patientId){
        RegisterDto registerDto = registerService.create(registerCreateDto, patientId);
        if(registerDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(registerDto, HttpStatus.CREATED);
    }


    @DeleteMapping("/{registerId}")
    public ResponseEntity<String> deleteById(@PathVariable ("registerId") Long registerId){
        registerService.deleteById(registerId);
        return new ResponseEntity<>("Register deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{registerId}")
    public ResponseEntity<RegisterDto> update(@PathVariable ("registerId") Long registerId,
                                              @RequestBody RegisterUpdateDto registerUpdateDto) {
        RegisterDto registerDto = registerService.update(registerId, registerUpdateDto);
        if(registerDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(registerDto, HttpStatus.OK);
    }









}
