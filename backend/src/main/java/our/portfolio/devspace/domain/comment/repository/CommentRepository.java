package our.portfolio.devspace.domain.comment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.comment.entity.Comment;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(Long postId);

    default Comment findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException("Comment Id " + id + "에 해당하는 댓글이 없습니다.", ErrorDetail.COMMENT_NOT_FOUND));
    }
}
