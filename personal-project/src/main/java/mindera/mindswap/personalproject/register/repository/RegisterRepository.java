package mindera.mindswap.personalproject.register.repository;


import mindera.mindswap.personalproject.register.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
}
