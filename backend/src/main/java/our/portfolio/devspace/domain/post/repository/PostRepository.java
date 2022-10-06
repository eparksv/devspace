package our.portfolio.devspace.domain.post.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findFirst11BySecret(Boolean secret, Sort sort);

    List<Post> findFirst11ByIdLessThanAndSecret(Long id, Boolean secret, Sort sort);

    default Post findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException("Post Id " + id + "에 해당하는 포스트가 없습니다.", ErrorDetail.POSTS_NOT_FOUND));
    }
}
