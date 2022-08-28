package our.portfolio.devspace.domain.post.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.domain.UserId;
import our.portfolio.devspace.domain.post.dto.PostCreationRequestDto;
import our.portfolio.devspace.domain.post.dto.PostCreationResponseDto;
import our.portfolio.devspace.domain.post.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/posts")
    public ResponseEntity<HttpResponseBody<PostCreationResponseDto>> createPost(@RequestBody @Valid PostCreationRequestDto dto, @UserId Long userId) {
        PostCreationResponseDto responseDto = postService.createPost(userId, dto);

        // HTTP Status Code: 201 Created, Response Body: { message, data: PostCreationResponse}
        HttpResponseBody<PostCreationResponseDto> body = new HttpResponseBody<>("등록되었습니다.", responseDto);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
