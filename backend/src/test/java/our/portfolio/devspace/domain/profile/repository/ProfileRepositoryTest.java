/*
package our.portfolio.devspace.domain.profile.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import our.portfolio.devspace.utils.EntityFactory;
import our.portfolio.devspace.common.mapper.EntityMapper;
import our.portfolio.devspace.common.mapper.ProfileMapper;
import our.portfolio.devspace.common.mapper.ProfileMapperImpl;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.repository.JobRepository;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest.ReferenceLinkDto;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.domain.user.repository.UserRepository;

@DataJpaTest
@Import({ProfileMapperImpl.class})
@ExtendWith(SpringExtension.class)
class ProfileRepositoryTest {

    @MockBean
    EntityMapper entityMapper;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProfileMapper profileMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    JobRepository jobRepository;

    @BeforeEach
    void setUp() {
        User user = EntityFactory.userEntity();
        Job job = EntityFactory.jobEntity();

        userRepository.save(user);
        jobRepository.save(job);
    }

    @Test
    public void createProfileTest() {
        // GIVEN
        Profile profile = profileMapper.toEntity(1L, profileCreationDto());
        assertThat(profile.getId()).isEqualTo(1L);

        // WHEN
        profileRepository.save(profile);

        // THEN
        assertThat(profile.getId()).isNotNull();

    }

    private CreateProfileRequest profileCreationDto() {
        List<ReferenceLinkDto> referenceLinks = List.of(
            new ReferenceLinkDto("Github", "https://www.github.com/"),
            new ReferenceLinkDto("Google", "http://www.google.com/"));

        return CreateProfileRequest.builder()
            .name("이름")
            .introduction("자기소개")
            .jobId(1)
            .referenceLinks(referenceLinks)
            .company("Devspace")
            .career("1년")
            .build();
    }
}
*/
