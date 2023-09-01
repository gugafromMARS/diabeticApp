package mindera.mindswap.personalproject.repository;

import mindera.mindswap.personalproject.model.institution.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
}
