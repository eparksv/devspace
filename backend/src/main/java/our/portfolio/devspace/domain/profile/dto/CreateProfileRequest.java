package our.portfolio.devspace.domain.profile.dto;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.entity.ReferenceLink;
import our.portfolio.devspace.domain.user.entity.User;

@Getter
public class CreateProfileRequestDto {

    @NotNull(message = "이름을 입력하세요.")
    @Length(min = 2, max = 12, message = "이름은 2자 이상, 12자 이하로 입력하세요.")
    private final String name;

    @NotBlank(message = "자기소개를 입력하세요.")
    @Length(max = 255, message = "자기소개는 255자 이하로 입력하세요.")
    private final String introduction;

    @NotNull(message = "직군을 선택하세요.")
    private final Integer jobId;

    @Length(max = 30, message = "회사명은 30자 이하로 입력하세요.")
    private final String company;

    @Length(max = 30, message = "경력 기간은 30자 이하로 입력하세요.")
    private final String career;

    @Valid
    @Size(max = 5, message = "링크는 5개 이하로 입력하세요.")
    private final List<ReferenceLinkDto> referenceLinks;

    @Builder
    public CreateProfileRequestDto(String name, String introduction, Integer jobId, String company, String career, List<ReferenceLinkDto> referenceLinks) {
        this.name = name;
        this.introduction = introduction;
        this.jobId = jobId;
        this.company = company;
        this.career = career;
        this.referenceLinks = referenceLinks;
    }

    public Profile toEntity(User user, Job job) {
        return Profile.builder()
            .user(user)
            .name(this.name)
            .job(job)
            .introduction(this.introduction)
            .referenceLinks(createReferenceLink())
            .build();
    }

    private List<ReferenceLink> createReferenceLink() {
        return this.referenceLinks.stream()
            .map(link -> ReferenceLink.builder()
                .description(link.title)
                .url(link.url)
                .build())
            .collect(Collectors.toList());
    }

    @Getter
    @AllArgsConstructor
    public static class ReferenceLinkDto {

        @Length(max = 10, message = "링크 이름은 10자 이하로 입력하세요.")
        private String title;

        @URL(regexp = "^(http|https):\\/\\/.*", message = "URL 형식이 유효하지 않습니다.")
        private String url;
    }
}
