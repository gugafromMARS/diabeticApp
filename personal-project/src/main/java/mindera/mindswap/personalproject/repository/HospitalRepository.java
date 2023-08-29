package mindera.mindswap.personalproject.repository;

import mindera.mindswap.personalproject.model.hospital.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
