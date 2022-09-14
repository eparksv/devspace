package our.portfolio.devspace.common.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.profile.dto.SimpleProfileResponse;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.utils.EntityFactory;
import our.portfolio.devspace.utils.dummy.DummyProfile;

@ExtendWith(MockitoExtension.class)
class ProfileMapperTest {

    @InjectMocks
    ProfileMapperImpl profileMapper;

    @Test
    void toSimpleProfileResponse() throws IllegalAccessException {
        // ** Given **
        Profile profile = EntityFactory.profileEntity(new DummyProfile(1L));

        // ** When **
        SimpleProfileResponse responseDto = profileMapper.toSimpleProfileResponse(profile);

        // ** Then **
        assertThat(responseDto)
            .usingRecursiveComparison()
            .withEqualsForFields((dto, entity) -> dto.equals(((Job) entity).getTitle()), "job")
            .isEqualTo(profile);
    }
}