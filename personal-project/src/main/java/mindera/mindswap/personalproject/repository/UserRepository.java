package mindera.mindswap.personalproject.repository;

import mindera.mindswap.personalproject.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public User findByEmail(String email);
}
