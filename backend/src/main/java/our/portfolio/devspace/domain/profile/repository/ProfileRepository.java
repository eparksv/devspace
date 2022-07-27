package our.portfolio.devspace.domain.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.profile.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
