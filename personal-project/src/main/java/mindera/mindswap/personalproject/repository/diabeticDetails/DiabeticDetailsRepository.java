package mindera.mindswap.personalproject.repository.diabeticDetails;

import mindera.mindswap.personalproject.model.diabeticDetails.DiabeticDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiabeticDetailsRepository extends JpaRepository<DiabeticDetails, Long> {
}
