package our.portfolio.devspace.common;

import java.util.ArrayList;
import java.util.List;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.post.dto.PostCreationRequestDto;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest.ReferenceLinkDto;

public class DtoFactory {
    public static PostCreationRequestDto postCreationRequestDto() {
        return PostCreationRequestDto.builder()
            .title("제목")
            .content("본문")
            .secret(false)
            .hashtags(List.of("#태그1", "#태그2", "#태그3"))
            .build();
    }

    public static CreateProfileRequest createProfileRequest() {
        return CreateProfileRequest.builder()
            .company("회사")
            .introduction("자기소개")
            .jobId(1)
            .career("1년")
            .name("이름")
            .referenceLinks(List.of(
                new ReferenceLinkDto("구글", "https://www.google.com"),
                new ReferenceLinkDto("깃허브", "https://www.github.com")))
            .build();
    }

    public static List<JobResponse> jobResponses(int size) {
        List<JobResponse> jobResponses = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            jobResponses.add(new JobResponse(i, "개발자" + i));
        }
        return jobResponses;
    }
}
