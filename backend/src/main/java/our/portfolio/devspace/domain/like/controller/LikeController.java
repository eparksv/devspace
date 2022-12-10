package our.portfolio.devspace.domain.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.domain.UserId;
import our.portfolio.devspace.domain.like.dto.CreateLikeRequest;
import our.portfolio.devspace.domain.like.dto.CreateLikeResponse;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.service.LikeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {

    //해당 게시판에 좋아요를 누를 사람 목록 반환(8명 최신순)
    // 포스팅트 id로 profile id 반환

    private final LikeService likeService;

    /**
     * post id로 종아요 회원 목록을 요청하면 해당하는 좋아요 회원 목록을 반환한다.
     *
     * @param id
     * @return 결과 메시지와 {@link GetLikeResponse}의 List를 담은 {@link HttpResponseBody}, Status 200 OK
     */
    @GetMapping("api/like/{id}")
    public ResponseEntity<HttpResponseBody<List<GetLikeResponse>>> listLikes(@PathVariable Long id, Pageable pageable) {

        HttpResponseBody<List<GetLikeResponse>> body
                = new HttpResponseBody<>("좋아요 회원 목록이 조회되었습니다.", likeService.listLikes(id, pageable));
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    /**
     * 좋아요를 생성하면 생성된 좋아요의 ID를 반환한다.
     *
     * @param dto    {@link CreateLikeRequest} 작성한 포스팅의 DTP
     * @param userId {@link Long} 작성자의 ID
     * @return 결과 메시지와 {@link CreateLikeResponse}를 담은 {@link HttpResponseBody}, Status 201 CREATED
     */
    @PostMapping("api/like")
    public ResponseEntity<HttpResponseBody<CreateLikeResponse>> createLike(@RequestBody CreateLikeRequest likeRequest, @UserId Long userId) {

        CreateLikeResponse responseDto = likeService.createLike(likeRequest, userId);

        HttpResponseBody<CreateLikeResponse> body = new HttpResponseBody<>("등록되었습니다.", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

}
