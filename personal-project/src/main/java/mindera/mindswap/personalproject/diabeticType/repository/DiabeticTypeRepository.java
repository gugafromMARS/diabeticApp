package mindera.mindswap.personalproject.diabeticType.repository;

import mindera.mindswap.personalproject.diabeticType.model.DiabeticType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiabeticTypeRepository extends JpaRepository<DiabeticType, Long> {
}
