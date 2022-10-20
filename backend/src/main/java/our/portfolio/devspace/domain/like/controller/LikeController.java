package our.portfolio.devspace.domain.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
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
    public ResponseEntity<HttpResponseBody<List<GetLikeResponse>>> listLikes(@PathVariable Long id,Pageable pageable) {

        HttpResponseBody<List<GetLikeResponse>> body
                = new HttpResponseBody<>("좋아요 회원 목록이 조회되었습니다.", likeService.listLikes(id, pageable));
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


}
