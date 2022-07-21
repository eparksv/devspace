package our.portfolio.devspace.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
