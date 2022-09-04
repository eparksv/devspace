package our.portfolio.devspace.domain.profile.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileResponse;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.common.mapper.ProfileMapper;
import our.portfolio.devspace.domain.profile.repository.ProfileRepository;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;

    @Transactional
    public CreateProfileResponse createProfile(Long userId, CreateProfileRequest dto) {
        // 이미 등록된 프로필이 있으면 예외를 던진다.
        throwIfExists(userId);

        // User ID와 DTO를 Entity로 변환한다.
        Profile profile = profileMapper.toEntity(userId, dto);

        // Profile을 저장하고 Profile ID를 DTO로 변환한 후 반환한다.
        return profileMapper.toCreateProfileResponse(profileRepository.save(profile));
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
