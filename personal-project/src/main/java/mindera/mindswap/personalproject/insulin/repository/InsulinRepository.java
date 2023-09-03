package mindera.mindswap.personalproject.insulin.repository;

import mindera.mindswap.personalproject.insulin.model.Insulin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsulinRepository extends JpaRepository<Insulin, Long> {
}
