package mindera.mindswap.personalproject.diabeticDetails.repository;

import mindera.mindswap.personalproject.diabeticDetails.model.DiabeticDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiabeticDetailsRepository extends JpaRepository<DiabeticDetails, Long> {
}
