package mindera.mindswap.personalproject.register.repository;


import mindera.mindswap.personalproject.register.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    Register findByLocalDateTime(LocalDateTime localDateTime);
    List<Register> findAllByPatientId(Long patientId);
}
