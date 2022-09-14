package our.portfolio.devspace.utils.factory;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest.ReferenceLinkDto;
import our.portfolio.devspace.domain.profile.dto.SimpleProfileResponse;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;

@Setter
@Getter
@NoArgsConstructor
public class ProfileFactory {

    private Long id;
    private String company = "데브스페이스";
    private String introduction = "자기소개";
    private String name = "이름";
    private String career = "경력기간";
    private List<ReferenceLinkDto> referenceLinks = List.of(
        new ReferenceLinkDto("구글", "https://www.google.com"),
        new ReferenceLinkDto("깃허브", "https://www.github.com")
    );
    private JobFactory job = new JobFactory(1);

    public ProfileFactory(Long id) {
        this.id = id;
    }

    public SimpleProfileResponse simpleProfileResponse() {
        return SimpleProfileResponse.builder()
            .id(this.id)
            .job(this.job.getTitle())
            .company(this.company)
            .name(this.name)
            .build();
    }

    public CreateProfileRequest createProfileRequest() {
        return CreateProfileRequest.builder()
            .company(this.company)
            .introduction(this.introduction)
            .jobId(this.job.getId())
            .career(this.career)
            .name(this.name)
            .referenceLinks(this.referenceLinks)
            .build();
    }

    public Profile profileEntity() throws IllegalAccessException {
        List<ReferenceLink> referenceLinks = this.referenceLinks
            .stream()
            .map(referenceLinkDto -> new ReferenceLink(referenceLinkDto.getTitle(), referenceLinkDto.getUrl()))
            .collect(Collectors.toList());

        Profile entity = Profile.builder()
            .company(this.company)
            .introduction(this.introduction)
            .name(this.name)
            .career(this.career)
            .referenceLinks(referenceLinks)
            .job(this.job.jobEntity())
            .user(new UserFactory(1L).userEntity())
            .build();

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }
}
