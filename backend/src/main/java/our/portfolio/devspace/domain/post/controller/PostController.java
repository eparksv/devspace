package our.portfolio.devspace.domain.post.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.domain.UserId;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.dto.CreatePostResponse;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.service.PostService;
import our.portfolio.devspace.domain.post.service.pagination.PostPaginationService;
import our.portfolio.devspace.domain.post.service.pagination.PostPaginationServiceFactory;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final PostPaginationServiceFactory factory;

    @PostMapping("/api/posts")
    public ResponseEntity<HttpResponseBody<CreatePostResponse>> createPost(@RequestBody @Valid CreatePostRequest dto, @UserId Long userId) {
        CreatePostResponse responseDto = postService.createPost(userId, dto);

        // HTTP Status Code: 201 Created, Response Body: { message, data: PostCreationResponse}
        HttpResponseBody<CreatePostResponse> body = new HttpResponseBody<>("등록되었습니다.", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    /**
     * 포스팅 목록을 요청하면 {@link GetPostsQuery.PostFilter}를 통해 포스팅의 검색 서비스 클래스를 결정하고, 해당하는 포스팅 목록을 반환한다.
     *
     * @param query {@link GetPostsQuery} 쿼리스트링의 DTO
     * @return 결과 메시지와 {@link GetPostsResponse}를 담은 {@link HttpResponseBody}, Status 200 OK
     */
    @GetMapping("/api/posts")
    public ResponseEntity<HttpResponseBody<GetPostsResponse>> getPosts(@ModelAttribute @Valid GetPostsQuery query) {
        // PostPaginationServiceFactory로 PostPaginationService의 구현 클래스를 받는다.
        PostPaginationService service = factory.getService(query.getFilter());
        GetPostsResponse responseDto = service.getPosts(query);

        HttpResponseBody<GetPostsResponse> body = new HttpResponseBody<>("조회되었습니다.", responseDto);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
