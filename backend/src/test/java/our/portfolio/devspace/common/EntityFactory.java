package our.portfolio.devspace.common;

import static our.portfolio.devspace.common.CommonTestUtils.setIdField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;
import our.portfolio.devspace.domain.user.entity.Role;
import our.portfolio.devspace.domain.user.entity.User;

public class EntityFactory {

    public static Profile profileEntityWithId(CreateProfileRequest dto, Long id) throws IllegalAccessException {
        return (Profile) setIdField(profileEntity(dto), id);
    }

    public static Profile profileEntity(CreateProfileRequest dto) throws IllegalAccessException {
        return Profile.builder()
            .company(dto.getCompany())
            .introduction(dto.getIntroduction())
            .name(dto.getName())
            .career(dto.getCareer())
            .referenceLinks(
                dto.getReferenceLinks()
                    .stream()
                    .map(link -> ReferenceLink.builder().title(link.getTitle()).url(link.getUrl()).build())
                    .collect(Collectors.toList()))
            .job(jobEntityWithId("개발자", 1))
            .user(userEntity())
            .build();
    }

    public static List<Job> jobEntities(String title, int size) throws IllegalAccessException {
        List<Job> jobEntities = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            jobEntities.add(jobEntityWithId(title + i, i));
        }
        return jobEntities;
    }

    public static Job jobEntityWithId(String title, Integer id) throws IllegalAccessException {
        return (Job) setIdField(new Job(JobType.DEVELOPER, title), id);
    }

    public static User userEntity() {
        return User.builder()
            .role(Role.USER)
            .subject("12345")
            .provider(OAuth2Provider.GOOGLE)
            .build();
    }

    public static Post postEntity(CreatePostRequest dto) throws IllegalAccessException {
        return Post.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .secret(dto.getSecret())
            .profile(profileEntityWithId(DtoFactory.createProfileRequest(), 1L))
            .hashtags(
                dto.getHashtags().stream()
                    .map(Hashtag::new)
                    .collect(Collectors.toList()))
            .build();
    }

    public static Post postEntityWithId(CreatePostRequest dto, Long id) throws IllegalAccessException {
        return (Post) setIdField(postEntity(dto), id);
    }
}
