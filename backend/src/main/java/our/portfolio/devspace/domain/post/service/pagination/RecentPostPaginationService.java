package our.portfolio.devspace.domain.post.service.pagination;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;

@Transactional
@Service
public class RecentPostPaginationService extends PostPaginationService {

    public RecentPostPaginationService(PostRepository postRepository, PostMapper postMapper) {
        super(postRepository, postMapper);
    }

    @Override
    public GetPostsResponse getPosts(GetPostsQuery query) {
        List<Post> posts;

        if (isFirstPage(query)) {
            posts = postRepository.findFirst11BySecret(false, query.getSort());
        } else {
            posts = postRepository.findFirst11ByIdLessThanAndSecret(query.getCursor(), false, query.getSort());
        }

        postsExistOrThrow(posts);

        return createResponse(query, posts);
    }

    @Override
    protected String createNextRequestUriIfHasNext(List<Post> posts, GetPostsQuery query) {
        return hasNext(posts)
            ? String.format("/api/posts?%s&%s", query.getNextCursorQueryString(posts), query.getSortQueryString())
            : null;
    }
}
