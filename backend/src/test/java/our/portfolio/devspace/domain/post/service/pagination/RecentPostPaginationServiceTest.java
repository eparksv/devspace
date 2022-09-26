package our.portfolio.devspace.domain.post.service.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.utils.factory.PostFactory;

@ExtendWith(MockitoExtension.class)
class RecentPostPaginationServiceTest {

    @Mock
    private PostRepository repository;

    @Mock
    private PostMapper mapper;

    @InjectMocks
    private RecentPostPaginationService service;

    @Test
    void getPosts() {
    }

    @Test
    @DisplayName("createNextRequestUriIfHasNext: 다음 페이지가 있으면 다음 페이지의 요청 URI를 반환한다.")
    void createNextRequestUriShouldReturnNextRequestUri() throws IllegalAccessException {
        // ** Given **
        List<Post> posts = PostFactory.postEntities(21);
        GetPostsQuery query = new GetPostsQuery();

        // ** When **
        String nextRequestUri = service.createNextRequestUriIfHasNext(posts, query);

        // ** Then **
        assertThat(nextRequestUri).isEqualTo("/api/posts?%s&%s", query.getNextCursorQueryString(posts), query.getSortQueryString());
    }

    @Test
    @DisplayName("createNextRequestUriIfHasNext(): 다음 페이지가 없으면 null을 반환한다.")
    void createNextRequestUriReturnNull() throws IllegalAccessException {
        // ** Given **
        List<Post> posts = PostFactory.postEntities(20);
        GetPostsQuery query = new GetPostsQuery();

        // ** When **
        String nextRequestUri = service.createNextRequestUriIfHasNext(posts, query);

        // ** Then **
        assertThat(nextRequestUri).isNull();
    }
}