package our.portfolio.devspace.domain.follow.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.common.mapper.FollowMapper;
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;
import our.portfolio.devspace.domain.follow.dto.CreateFollowResponse;
import our.portfolio.devspace.domain.follow.entity.Follow;
import our.portfolio.devspace.domain.follow.repository.FollowRepository;
import our.portfolio.devspace.exception.CustomException;

import static our.portfolio.devspace.exception.ErrorDetail.AREADY_EXIST_VALUE;
import static our.portfolio.devspace.exception.ErrorDetail.INVALID_INPUT_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {

    private final FollowMapper followMapper;

    private final FollowRepository followRepository;

    public CreateFollowResponse createFollow(CreateFollowRequest followRequest, Long userId) {

        if (userId.equals(followRequest.getId())) {
            throw new CustomException(INVALID_INPUT_VALUE);
        }

        Long id = followRepository.findIdByFollowerIdAndFolloweeId(userId, followRequest.getId());

        if (followRepository.findIdByFollowerIdAndFolloweeId(userId, followRequest.getId()) != 0) {
            throw new CustomException(AREADY_EXIST_VALUE);
        }

        Follow follow = followMapper.toEntity(followRequest, userId);
        followRepository.save(follow);

        return followMapper.toCreateFollowResponse(follow);
    }
}
