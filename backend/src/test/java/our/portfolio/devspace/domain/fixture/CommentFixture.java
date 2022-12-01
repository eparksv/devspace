package our.portfolio.devspace.domain.fixture;

import java.util.List;
import our.portfolio.devspace.domain.comment.entity.Comment;
import our.portfolio.devspace.domain.post.entity.Post;

public class CommentFixture {
    public static final String COMMENT_CONTENT = "안녕하세요!";

    public static Comment createComment(Post post) {
        return Comment.builder()
            .author(UserFixture.createUser())
            .content(COMMENT_CONTENT)
            .post(post)
            .build();
    }

    public static List<Comment> createComments(Post post) {
        return List.of(createComment(post), createComment(post), createComment(post));
    }
}
