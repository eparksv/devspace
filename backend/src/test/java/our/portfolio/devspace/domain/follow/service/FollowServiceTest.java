package our.portfolio.devspace.domain.follow.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.FollowMapper;
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;
import our.portfolio.devspace.domain.follow.dto.CreateFollowResponse;
import our.portfolio.devspace.domain.follow.entity.Follow;
import our.portfolio.devspace.domain.follow.repository.FollowRepository;
import our.portfolio.devspace.utils.CommonTestUtils;
import our.portfolio.devspace.utils.factory.FollowFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock
    FollowMapper followMapper;

    @Mock
    FollowRepository followRepository;

    @InjectMocks
    FollowService followService;

    @Test
    @DisplayName("팔로우 생성에 성공하면, 팔로우한 유저 ID를 반환한다.")
    void shouldReturnFolloweeIdWhenCreateFollow() throws IllegalAccessException {
        // ** Given **
        Long id = 1L;
        Long followerId = 1L;
        Long followeeId = 2L;

        CreateFollowRequest requestDto = new FollowFactory(followeeId).createFollowRequest();

        FollowFactory followFactory = new FollowFactory(id, followerId, followeeId);
        Follow follow = followFactory.followEntity();

        given(followMapper.toEntity(any(CreateFollowRequest.class), anyLong())).willReturn(follow);
        given(followRepository.save(follow)).will(invocation -> {
            CommonTestUtils.setIdField(invocation.getArgument(0), followeeId);
            return follow;
        });
        given(followRepository.findIdByFollowerIdAndFolloweeId(followerId, followeeId)).willReturn(0L);
        given(followMapper.toCreateFollowResponse(any(Follow.class))).willReturn(new CreateFollowResponse(followeeId));

        // ** When **
        CreateFollowResponse responseDto = followService.createFollow(requestDto, followerId);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(followeeId);
    }

}