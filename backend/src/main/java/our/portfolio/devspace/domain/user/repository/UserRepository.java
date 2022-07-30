package our.portfolio.devspace.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySubjectAndProvider(String subject, OAuth2Provider provider);
}
