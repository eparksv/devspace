package our.portfolio.devspace.utils;

import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.utils.dummy.DummyPost;

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
}
