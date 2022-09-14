package our.portfolio.devspace.domain.post.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetPostsResponse {

    private final int count;
    private final List<PostPreviewResponse> posts;
    private final boolean hasNext;
    private final String nextRequestUri;

    @Builder
    public GetPostsResponse(int count, List<PostPreviewResponse> posts, boolean hasNext, String nextRequestUri) {
        this.count = count;
        this.posts = posts;
        this.hasNext = hasNext;
        this.nextRequestUri = nextRequestUri;
    }
}
