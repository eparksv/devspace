package our.portfolio.devspace.domain.post.service.pagination;

import java.util.List;
import lombok.RequiredArgsConstructor;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.dto.PostPreviewResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;

@RequiredArgsConstructor
public abstract class PostPaginationService {

    public static final int PAGE_SIZE = 20;
    protected final PostRepository postRepository;
    private final PostMapper postMapper;

    public abstract GetPostsResponse getPosts(GetPostsQuery query);

    protected abstract String createNextRequestUriIfHasNext(List<Post> posts, GetPostsQuery query);

    protected GetPostsResponse createResponse(GetPostsQuery query, List<Post> posts) {
        return GetPostsResponse.builder()
            .posts(createPostPreviewResponses(posts))
            .nextRequestUri(createNextRequestUriIfHasNext(posts, query))
            .build();
    }

    protected boolean hasNext(List<Post> posts) {
        return posts.size() > PAGE_SIZE; // 조회된 포스팅이 페이지 당 포스팅 개수를 초과하면 다음 페이지가 존재함
    }

    private List<PostPreviewResponse> createPostPreviewResponses(List<Post> posts) {
        List<PostPreviewResponse> postPreviewResponses;

        if (hasNext(posts)) {
            postPreviewResponses = postMapper.toPostPreviewResponses(posts.subList(0, PAGE_SIZE));
        } else {
            postPreviewResponses = postMapper.toPostPreviewResponses(posts);
        }

        return postPreviewResponses;
    }
}