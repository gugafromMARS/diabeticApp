package mindera.mindswap.personalproject.repository;

import mindera.mindswap.personalproject.model.insulin.Insulin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsulinRepository extends JpaRepository<Insulin, Long> {
}
