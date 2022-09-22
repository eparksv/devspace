package our.portfolio.devspace.domain.post.service.pagination;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.common.mapper.PostMapperImpl;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.utils.factory.PostFactory;

@ExtendWith(MockitoExtension.class)
class PostPaginationServiceTest {

    @Mock
    private PostRepository repository;

    @Mock
    private PostMapperImpl mapper;

    @InjectMocks
    private MockPostPaginationService service;

    @Test
    @DisplayName("createResponse: List<Post>의 size가 Page Size보다 작으면 List의 모든 Post를 GetPostsResponse로 매핑하여 반환한다.")
    void createResponseShouldReturnGetPostsResponseWith() throws IllegalAccessException {
        // ** Given **
        GetPostsQuery query = new GetPostsQuery();
        List<Post> posts = PostFactory.postEntities(10);
        given(mapper.toPostPreviewResponses(anyList())).will(invocation -> {
            List<Post> postsParam = invocation.getArgument(0);
            return PostFactory.postPreviewResponses(postsParam.size());
        });

        // ** When **
        GetPostsResponse getPostsResponse = service.createResponse(query, posts);

        // ** Then **
        // assertThat(getPostsResponse.getCount()).isNull(); TODO 검색 결과 개수 구현 여부 확정 후 작성
        assertThat(getPostsResponse.getPosts()).hasSize(posts.size());
        assertThat(getPostsResponse.getNextRequestUri()).isNull();
    }

    @Test
    @DisplayName("createResponse: List<Post>의 size가 Page Size보다 크면 Page Size만큼의 Post만 GetPostsResponse로 매핑하여 반환한다.")
    void createResponse() throws IllegalAccessException {
        // ** Given **
        GetPostsQuery query = new GetPostsQuery();
        List<Post> posts = PostFactory.postEntities(21);
        given(mapper.toPostPreviewResponses(anyList())).will(invocation -> {
            List<Post> postsParam = invocation.getArgument(0);
            return PostFactory.postPreviewResponses(postsParam.size());
        });

        // ** When **
        GetPostsResponse getPostsResponse = service.createResponse(query, posts);

        // ** Then **
        assertThat(getPostsResponse.getPosts()).hasSize(PostPaginationService.PAGE_SIZE);
        assertThat(getPostsResponse.getNextRequestUri()).isNotNull();
    }

    @Test
    @DisplayName("hasNext: List<Post>의 size가 Page Size보다 크면 true를 반환한다.")
    void hasNextShouldReturnTrue() throws IllegalAccessException {
        // ** Given **
        List<Post> posts = PostFactory.postEntities(PostPaginationService.PAGE_SIZE + 1);

        // ** When **
        boolean result = service.hasNext(posts);

        // ** Then **
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("hasNext: List<Post>의 size가 Page Size보다 작거나 같으면 false를 반환한다.")
    void hasNextShouldReturnFalse() throws IllegalAccessException {
        // ** Given **
        List<Post> posts = PostFactory.postEntities(PostPaginationService.PAGE_SIZE);

        // ** When **
        boolean result = service.hasNext(posts);

        // ** Then **
        assertThat(result).isFalse();
    }

    static class MockPostPaginationService extends PostPaginationService {

        public MockPostPaginationService(PostRepository postRepository, PostMapper postMapper) {
            super(postRepository, postMapper);
        }

        @Override
        public GetPostsResponse getPosts(GetPostsQuery query) {
            return null;
        }

        @Override
        protected String createNextRequestUriIfHasNext(List<Post> posts, GetPostsQuery query) {
            return hasNext(posts) ? "Not Null" : null;
        }
    }
}