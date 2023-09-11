package mindera.mindswap.personalproject.controller.register;


import mindera.mindswap.personalproject.dto.register.RegisterCreateDto;
import mindera.mindswap.personalproject.dto.register.RegisterUpdateDto;
import mindera.mindswap.personalproject.service.register.RegisterServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registers")
public class RegisterController {


    private RegisterServiceImp registerServiceImp;

    @Autowired
    public RegisterController(RegisterServiceImp registerServiceImp) {
        this.registerServiceImp = registerServiceImp;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(registerServiceImp.getAll());
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<?> create(@RequestBody RegisterCreateDto registerCreateDto, @PathVariable ("patientId") Long patientId){
        return new ResponseEntity<>(registerServiceImp.create(registerCreateDto, patientId), HttpStatus.CREATED);
    }


    @DeleteMapping("/{registerId}")
    public ResponseEntity<String> deleteById(@PathVariable ("registerId") Long registerId){
        registerServiceImp.deleteById(registerId);
        return new ResponseEntity<>("Register deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{registerId}")
    public ResponseEntity<?> update(@PathVariable ("registerId") Long registerId,
                                              @RequestBody RegisterUpdateDto registerUpdateDto) {
        return new ResponseEntity<>(registerServiceImp.update(registerId, registerUpdateDto), HttpStatus.OK);
    }


}
