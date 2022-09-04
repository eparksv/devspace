package our.portfolio.devspace.common;

import java.util.stream.Collectors;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.post.dto.PostCreationRequestDto;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.entity.PostHashtag;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;
import our.portfolio.devspace.domain.user.entity.Role;
import our.portfolio.devspace.domain.user.entity.User;

public class EntityFactory {

    public static Profile profileEntityWithId(CreateProfileRequest dto, Long id) throws IllegalAccessException {
        return (Profile) CommonTestUtils.setIdField(profileEntity(dto), id);
    }

    public static Profile profileEntity(CreateProfileRequest dto) {
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
            .job(jobEntity())
            .user(userEntity())
            .build();
    }

    public static Job jobEntity() {
        return new Job(JobType.DEVELOPER, "개발자");
    }

    public static User userEntity() {
        return User.builder()
            .role(Role.USER)
            .subject("12345")
            .provider(OAuth2Provider.GOOGLE)
            .build();
    }

    public static Post postEntity(PostCreationRequestDto dto) throws IllegalAccessException {
        return Post.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .secret(dto.getSecret())
            .profile(profileEntityWithId(DtoFactory.createProfileRequest(), 1L))
            .hashtagsOfPost(
                dto.getHashtags().stream()
                    .map(EntityFactory::postHashtag)
                    .collect(Collectors.toList()))
            .build();
    }

    public static Post postEntityWithId(PostCreationRequestDto dto, Long id) throws IllegalAccessException {
        return (Post) CommonTestUtils.setIdField(postEntity(dto), id);
    }

    public static PostHashtag postHashtag(String name) {
        return new PostHashtag(new Hashtag(name));
    }

}
