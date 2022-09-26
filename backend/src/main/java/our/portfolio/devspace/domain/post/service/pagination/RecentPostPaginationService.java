package our.portfolio.devspace.domain.post.service.pagination;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@Transactional
@Service
public class RecentPostPaginationService extends PostPaginationService {

    public RecentPostPaginationService(PostRepository postRepository, PostMapper postMapper) {
        super(postRepository, postMapper);
    }

    @Override
    public GetPostsResponse getPosts(GetPostsQuery query) {
        List<Post> posts;

        if (query.getCursor() == null) {
            posts = postRepository.findFirst11BySecret(false, query.getSort());
        } else {
            posts = postRepository.findFirst11ByIdLessThanAndSecret(query.getCursor(), false, query.getSort());
        }

        if (posts.size() == 0) {
            throw new CustomException("포스팅이 존재하지 않습니다.", ErrorDetail.POSTS_NOT_FOUND);
        }

        return createResponse(query, posts);
    }

    @Override
    protected String createNextRequestUriIfHasNext(List<Post> posts, GetPostsQuery query) {
        return hasNext(posts)
            ? String.format("/api/posts?%s&%s", query.getNextCursorQueryString(posts), query.getSortQueryString())
            : null;
    }
}
