package mindera.mindswap.personalproject.service;


import mindera.mindswap.personalproject.converter.InstitutionConverter;
import mindera.mindswap.personalproject.dto.institution.InstitutionCreateDto;
import mindera.mindswap.personalproject.dto.institution.InstitutionDto;
import mindera.mindswap.personalproject.dto.institution.InstitutionUpdateDto;
import mindera.mindswap.personalproject.model.institution.Institution;
import mindera.mindswap.personalproject.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstitutionService {


    private InstitutionRepository institutionRepository;
    private InstitutionConverter institutionConverter;


    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository, InstitutionConverter institutionConverter) {
        this.institutionRepository = institutionRepository;
        this.institutionConverter = institutionConverter;
    }

    public List<InstitutionDto> getAll() {
        return institutionRepository.findAll().stream()
                .map(institution -> institutionConverter.toDto(institution)).toList();
    }

    public InstitutionDto getById(Long institutionId) {
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
        return institutionConverter.toDto(institution);
    }


    public InstitutionDto create(InstitutionCreateDto institutionCreateDto) {
        Institution institution = institutionRepository.findByEmail(institutionCreateDto.getEmail());
        if(institution != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Institution already exists");
        }
        Institution newInstitution = institutionConverter.fromCreateDto(institutionCreateDto);
        institutionRepository.save(newInstitution);
        return institutionConverter.toDto(newInstitution);
    }

    public void delete(Long institutionId) {
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));
        institutionRepository.delete(institution);
    }

    public InstitutionDto update(Long institutionId, InstitutionUpdateDto institutionUpdateDto) {
        Institution existingInstitution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Institution not found"));

        //need to add @valid and @not null
        existingInstitution.setEmail(institutionUpdateDto.getEmail());
        existingInstitution.setAddress(institutionUpdateDto.getAddress());

        institutionRepository.save(existingInstitution);
        return institutionConverter.toDto(existingInstitution);
    }
}
