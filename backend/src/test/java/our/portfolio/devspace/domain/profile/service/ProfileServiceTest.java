package our.portfolio.devspace.domain.profile.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.ProfileMapper;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileResponse;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.repository.ProfileRepository;
import our.portfolio.devspace.utils.dummy.DummyProfile;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    ProfileMapper profileMapper;

    @Mock
    ProfileRepository profileRepository;

    @InjectMocks
    ProfileService profileService;

    @Test
    @DisplayName("프로필 등록에 성공하면, 등록된 프로필 ID를 반환한다.")
    void shouldReturnProfileIdWhenCreateProfile() throws IllegalAccessException {
        // ** Given **
        Long userId = 1L;
        CreateProfileRequest requestDto = new DummyProfile(1L).createProfileRequest();

        given(profileMapper.toEntity(anyLong(), any(CreateProfileRequest.class))).willReturn(new DummyProfile().profileEntity());
        given(profileMapper.toCreateProfileResponse(any(Profile.class))).willReturn(new CreateProfileResponse(userId));
        given(profileRepository.save(any(Profile.class))).willReturn(new DummyProfile(userId).profileEntity());

        // ** When **
        CreateProfileResponse responseDto = profileService.createProfile(userId, requestDto);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(userId);
    }
}