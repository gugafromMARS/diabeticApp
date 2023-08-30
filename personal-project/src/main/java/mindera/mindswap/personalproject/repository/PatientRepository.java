package mindera.mindswap.personalproject.repository;

import mindera.mindswap.personalproject.model.user.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  public Patient findByEmail(String email);
}
