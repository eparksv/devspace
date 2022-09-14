package our.portfolio.devspace.utils;

import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
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
}
