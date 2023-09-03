package mindera.mindswap.personalproject.patient.repository;

import mindera.mindswap.personalproject.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  public Patient findByEmail(String email);
}
