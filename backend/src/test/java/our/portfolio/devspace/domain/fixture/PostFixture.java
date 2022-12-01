package our.portfolio.devspace.domain.fixture;

import our.portfolio.devspace.domain.post.entity.Post;

public class PostFixture {
    public static final String POST_TITLE = "게시글 제목";
    public static final String POST_CONTENT = "게시글 내용";

    public static Post createPost() {
        return Post.builder()
            .title(POST_TITLE)
            .content(POST_CONTENT)
            .hashtags(HashtagFixture.createHashtags())
            .build();
    }
}
