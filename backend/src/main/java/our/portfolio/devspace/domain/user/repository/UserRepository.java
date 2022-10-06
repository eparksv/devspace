package our.portfolio.devspace.domain.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySubjectAndProvider(String subject, OAuth2Provider provider);

    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException("User Id " + id + "에 해당하는 유저가 없습니다.", ErrorDetail.USER_NOT_FOUND));
    }
}
