package mindera.mindswap.personalproject.institution.repository;

import mindera.mindswap.personalproject.institution.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Institution findByEmail(String email);
}
