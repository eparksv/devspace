package our.portfolio.devspace.domain.follow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.domain.UserId;
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;
import our.portfolio.devspace.domain.follow.dto.CreateFollowResponse;
import our.portfolio.devspace.domain.follow.service.FollowService;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("api/follow")
    public ResponseEntity<HttpResponseBody<CreateFollowResponse>> createFollow(
            @RequestBody CreateFollowRequest followRequest, @UserId Long userId) {

        CreateFollowResponse responseDto = followService.createFollow(followRequest, userId);

        HttpResponseBody<CreateFollowResponse> body = new HttpResponseBody<>("등록되었습니다.", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
