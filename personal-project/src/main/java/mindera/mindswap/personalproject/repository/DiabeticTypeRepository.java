package mindera.mindswap.personalproject.repository;

import mindera.mindswap.personalproject.model.type.DiabeticType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiabeticTypeRepository extends JpaRepository<DiabeticType, Long> {
}
