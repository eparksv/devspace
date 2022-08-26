package our.portfolio.devspace.domain.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    default Profile findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException("User Id " + id + "에 해당하는 사용자의 프로필이 없습니다.", ErrorDetail.PROFILE_NOT_FOUND));
    }

}
