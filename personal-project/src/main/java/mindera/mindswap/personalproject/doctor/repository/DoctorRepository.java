package mindera.mindswap.personalproject.doctor.repository;

import mindera.mindswap.personalproject.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    Doctor findByEmail(String email);
}
