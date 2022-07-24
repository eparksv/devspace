package our.portfolio.devspace.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.user.entity.UserRefreshToken;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
    Optional<UserRefreshToken> findByEmail(String email);
}
