package our.portfolio.devspace.domain.comment.controller;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.domain.comment.dto.CommentResponse;
import our.portfolio.devspace.domain.comment.dto.PostCommentRequest;
import our.portfolio.devspace.domain.comment.service.CommentService;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postComment(
        @PathVariable Long postId,
        @RequestBody @Valid PostCommentRequest request,
        Principal userPrincipal
    ) {
        return commentService.postComment(
            Long.parseLong(userPrincipal.getName()),
            postId,
            request.getContent()
        );
    }

    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(
        @PathVariable Long postId,
        @PathVariable Long commentId,
        Principal userPrincipal
    ) {
        commentService.deleteComment(
            Long.parseLong(userPrincipal.getName()),
            commentId
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getComments(
        @PathVariable Long postId
    ) {
        return commentService.getComments(postId);
    }
}
