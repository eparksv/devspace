package our.portfolio.devspace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.user.UserRefreshToken;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {
}
