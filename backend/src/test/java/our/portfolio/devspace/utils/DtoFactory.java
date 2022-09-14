package our.portfolio.devspace.utils;

import java.util.ArrayList;
import java.util.List;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.SimpleProfileResponse;
import our.portfolio.devspace.utils.dummy.DummyJob;
import our.portfolio.devspace.utils.dummy.DummyPost;
import our.portfolio.devspace.utils.dummy.DummyProfile;

public class DtoFactory {

    public static CreatePostRequest createPostRequest(DummyPost post) {
        return CreatePostRequest.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .secret(post.isSecret())
            .categoryId(post.getCategory().getId())
            .hashtags(post.getHashtags())
            .build();
    }

    public static CreateProfileRequest createProfileRequest(DummyProfile profile) {
        return CreateProfileRequest.builder()
            .company(profile.getCompany())
            .introduction(profile.getIntroduction())
            .jobId(profile.getJob().getId())
            .career(profile.getCareer())
            .name(profile.getName())
            .referenceLinks(profile.getReferenceLinks())
            .build();
    }

    public static SimpleProfileResponse simpleProfileResponse(DummyProfile profile) {
        return SimpleProfileResponse.builder()
            .id(profile.getId())
            .job(profile.getJob().getTitle())
            .company(profile.getCompany())
            .name(profile.getName())
            .build();
    }

    public static List<JobResponse> jobResponses(JobType type) {
        List<JobResponse> jobResponses = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            DummyJob job = new DummyJob(i);
            job.setType(type);
            job.setTitle(type.toString() + i);
            jobResponses.add(new JobResponse(i, job.getTitle()));
        }

        return jobResponses;
    }
}
