package our.portfolio.devspace.domain.comment.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.domain.comment.dto.CommentResponse;
import our.portfolio.devspace.domain.comment.entity.Comment;
import our.portfolio.devspace.domain.comment.repository.CommentRepository;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.domain.user.repository.UserRepository;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void deleteComment(Long userId, Long commentId) {
        Comment foundComment = commentRepository.findByIdOrThrow(commentId);
        if (!foundComment.getAuthor().getId().equals(userId)) {
            throw new CustomException("댓글 작성자와 삭제하려는 계정이 일치하지 않습니다.", ErrorDetail.AUTHOR_NOT_MATCHED);
        }
        commentRepository.deleteById(foundComment.getId());
    }

    public List<CommentResponse> getComments(Long postId) {
        Post foundPost = postRepository.findByIdOrThrow(postId);
        return foundPost.getComments().stream()
            .map(comment -> CommentResponse.of(
                comment.getAuthor().getId(),
                comment.getAuthor().getProfile().getName(),
                comment.getAuthor().getProfile().getImage(),
                comment.getAuthor().getProfile().getCompany(),
                comment.getAuthor().getProfile().getJob().getTitle(),
                comment.getContent(),
                comment.getCreatedDate()
            ))
            .collect(Collectors.toList());
    }
}
