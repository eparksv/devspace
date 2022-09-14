package our.portfolio.devspace.utils.dummy;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest.ReferenceLinkDto;
import our.portfolio.devspace.domain.profile.dto.SimpleProfileResponse;

@Setter
@Getter
@NoArgsConstructor
public class DummyProfile {

    private Long id;
    private String company = "데브스페이스";
    private String introduction = "자기소개";
    private String name = "이름";
    private String career = "경력기간";
    private List<ReferenceLinkDto> referenceLinks = List.of(
        new ReferenceLinkDto("구글", "https://www.google.com"),
        new ReferenceLinkDto("깃허브", "https://www.github.com")
    );
    private DummyJob job = new DummyJob(1);

    public DummyProfile(Long id) {
        this.id = id;
    }

    public static SimpleProfileResponse simpleProfileResponse(DummyProfile profile) {
        return SimpleProfileResponse.builder()
            .id(profile.getId())
            .job(profile.getJob().getTitle())
            .company(profile.getCompany())
            .name(profile.getName())
            .build();
    }
}
