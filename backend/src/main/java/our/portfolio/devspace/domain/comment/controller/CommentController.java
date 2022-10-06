package our.portfolio.devspace.domain.comment.controller;

import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.domain.comment.service.CommentService;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

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
}
