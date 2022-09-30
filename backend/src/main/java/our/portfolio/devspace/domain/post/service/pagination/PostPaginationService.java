package our.portfolio.devspace.domain.post.service.pagination;

import java.util.List;
import lombok.RequiredArgsConstructor;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.dto.PostPreviewResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@RequiredArgsConstructor
public abstract class PostPaginationService {

    public static final int PAGE_SIZE = 10;
    protected final PostRepository postRepository;
    private final PostMapper postMapper;

    public abstract GetPostsResponse getPosts(GetPostsQuery query);

    protected abstract String createNextRequestUriIfHasNext(List<Post> posts, GetPostsQuery query);

    protected boolean isFirstPage(GetPostsQuery query) {
        return query.getCursor() == null;
    }

    protected void postsExistOrThrow(List<Post> posts) {
        if (posts.size() == 0) {
            throw new CustomException("포스팅이 존재하지 않습니다.", ErrorDetail.POSTS_NOT_FOUND);
        }
    }

    protected GetPostsResponse createResponse(GetPostsQuery query, List<Post> posts) {
        return GetPostsResponse.builder()
            .posts(createPostPreviewResponses(posts))
            .nextRequestUri(createNextRequestUriIfHasNext(posts, query))
            .build();
    }

    /**
     * 다음 페이지가 존재하는지 판별한다.<br> Repository가 포스팅을 조회할 때, 한 페이지의 최대 포스팅 개수를 초과하여 조회되면 다음 페이지가 존재한다.
     *
     * @param posts 조회된 {@link Post}의 List
     * @return List의 크기가 한 페이지의 포스팅 개수보다 크면 true, 작거나 같으면 false
     */
    protected boolean hasNext(List<Post> posts) {
        return posts.size() > PAGE_SIZE; // 조회된 포스팅이 페이지 당 포스팅 개수를 초과하면 다음 페이지가 존재함
    }

    /**
     * 조회된 포스팅 목록에서 다음 페이지의 포스팅을 제외한 현재 페이지의 포스팅을 {@link PostPreviewResponse}로 변환하여 반환한다.
     *
     * @param posts 조회된 {@link Post}의 List
     * @return 변환된 {@link PostPreviewResponse}의 List
     */
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