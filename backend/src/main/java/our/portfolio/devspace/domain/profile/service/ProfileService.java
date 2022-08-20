package our.portfolio.devspace.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.service.JobService;
import our.portfolio.devspace.domain.profile.dto.CreateProfileDto;
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

    public CreateProfileDto createProfile(Long userId, CreateProfileDto dto) {
        User user = userService.getUserById(userId);
        Job job = jobService.getJobById(dto.getJobId());

        // 이름, 직군, 자기소개, 사용자 ID를 프로필로 추가한다.
        Profile profile = Profile.builder()
            .user(user)
            .name(dto.getName())
            .job(job)
            .introduction(dto.getIntroduction())
            .build();

        // 프로필을 저장하고 DTO로 변환한 후 리턴한다.
        return CreateProfileDto.from(profileRepository.save(profile));
    }

    public Profile getProfileById(Long userId) {
        return profileRepository.findById(userId).orElseThrow(() ->
            new CustomException("User Id " + userId + "에 해당하는 사용자의 프로필이 없습니다.", ErrorDetail.PROFILE_NOT_FOUND)
        );
    }
}
