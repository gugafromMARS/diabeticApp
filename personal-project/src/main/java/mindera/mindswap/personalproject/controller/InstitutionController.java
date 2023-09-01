package mindera.mindswap.personalproject.controller;


import mindera.mindswap.personalproject.dto.institution.InstitutionCreateDto;
import mindera.mindswap.personalproject.dto.institution.InstitutionDto;
import mindera.mindswap.personalproject.dto.institution.InstitutionUpdateDto;
import mindera.mindswap.personalproject.model.institution.Institution;
import mindera.mindswap.personalproject.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institutions")
public class InstitutionController {

    private InstitutionService institutionService;

    @Autowired
    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping
    public ResponseEntity<List<InstitutionDto>>getAll(){
        List<InstitutionDto> institutionDtos = institutionService.getAll();
        if(institutionDtos == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(institutionDtos, HttpStatus.OK);
    }

    @GetMapping("/{institutionId}")
    public ResponseEntity<InstitutionDto> getById(@PathVariable ("institutionId") Long institutionId){
        InstitutionDto institutionDto = institutionService.getById(institutionId);
        if(institutionDto == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(institutionDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstitutionDto> create(@RequestBody InstitutionCreateDto institutionCreateDto){
        InstitutionDto institutionDto = institutionService.create(institutionCreateDto);
        if(institutionDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(institutionDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{institutionId}")
    public ResponseEntity<String> deleteById(@PathVariable ("institutionId") Long institutionId){
        institutionService.delete(institutionId);
        return new ResponseEntity<>("Institution deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{institutionId}")
    public ResponseEntity<InstitutionDto> update(@PathVariable ("institutionId") Long institutionId, @RequestBody InstitutionUpdateDto institutionUpdateDto){
        InstitutionDto institutionDto = institutionService.update(institutionId, institutionUpdateDto);
        if(institutionDto == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(institutionDto, HttpStatus.OK);
    }
}
