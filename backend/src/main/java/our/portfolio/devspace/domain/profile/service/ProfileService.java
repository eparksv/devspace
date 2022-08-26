package our.portfolio.devspace.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.service.JobService;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileResponse;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.repository.ProfileRepository;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.domain.user.service.UserService;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private final UserService userService;
    private final JobService jobService;
    private final ProfileRepository profileRepository;

    @Transactional
    public CreateProfileResponse createProfile(Long userId, CreateProfileRequest dto) {
        // 이미 등록된 프로필이 있으면 예외를 던진다.
        throwIfExists(userId);

        // 연관관계 Entity를 찾아서 Profile Entity를 생성한다.
        User user = userService.getUserById(userId);
        Job job = jobService.getJobById(dto.getJobId());
        Profile profile = dto.toEntity(user, job);

        // 프로필을 저장하고 ID를 DTO로 변환한 후 리턴한다.
        return CreateProfileResponse.from(profileRepository.save(profile));
    }

    private void throwIfExists(Long userId) {
        if (profileRepository.existsById(userId)) {
            throw new CustomException("이미 등록된 프로필이 있습니다.", ErrorDetail.PROFILE_ALREADY_EXISTS);
        }
    }

    public Profile getProfileById(Long userId) {
        return profileRepository.findByIdOrThrow(userId);
    }
}
