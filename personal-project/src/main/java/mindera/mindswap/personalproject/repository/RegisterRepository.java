package mindera.mindswap.personalproject.repository;


import mindera.mindswap.personalproject.model.register.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
}
