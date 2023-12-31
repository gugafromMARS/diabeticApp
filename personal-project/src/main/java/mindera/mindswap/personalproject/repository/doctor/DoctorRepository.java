package mindera.mindswap.personalproject.repository.doctor;

import mindera.mindswap.personalproject.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


    Doctor findByEmail(String email);
}
