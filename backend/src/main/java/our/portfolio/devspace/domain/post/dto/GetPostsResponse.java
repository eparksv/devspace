package our.portfolio.devspace.domain.post.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPostsResponse {

    private final Long count;
    private final List<PostPreviewResponse> posts;
    private final String nextRequestUri;

    @Builder
    public GetPostsResponse(Long count, List<PostPreviewResponse> posts, String nextRequestUri) {
        this.count = count;
        this.posts = posts;
        this.nextRequestUri = nextRequestUri;
    }
}
