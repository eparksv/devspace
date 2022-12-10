package our.portfolio.devspace.domain.like.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import our.portfolio.devspace.common.mapper.LikeMapper;
import our.portfolio.devspace.domain.like.dto.CreateLikeRequest;
import our.portfolio.devspace.domain.like.dto.CreateLikeResponse;
import our.portfolio.devspace.domain.like.dto.DeleteLikeResponse;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.entity.Like;
import our.portfolio.devspace.domain.like.repository.LikeRepository;
import our.portfolio.devspace.utils.CommonTestUtils;
import our.portfolio.devspace.utils.factory.LikeFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    LikeMapper likeMapper;

    @Mock
    LikeRepository likeRepository;

    @InjectMocks
    LikeService likeService;

    @ParameterizedTest(name = "게시판 ID = {0}에 해당하는  좋아요 회원 목록을 반환한다.")
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("좋아요 회원 목록을 반환한다.")
    void listLikes(int id) {

        // ** Given **
        Pageable pageable = PageRequest.of(0, 4);

        List<GetLikeResponse> likes = LikeFactory.GetLikeResponses(pageable);

        given(likeRepository.findLikeUserByPostId((long) id, pageable)).will(invocation ->
                LikeFactory.LikeDtos((long) id, pageable));

        // ** When **
        List<GetLikeResponse> responseDto = likeService.listLikes((long) id, pageable);

        // ** Then **
        assertThat(responseDto).usingRecursiveComparison().isEqualTo(likes);
    }

    @Test
    @DisplayName("좋아요 생성에 성공하면, 등록된 좋아요 게시글 ID를 반환한다.")
    void shouldReturnLikeIdWhenCreateLike() throws IllegalAccessException {
        // ** Given **
        Long postId = 1L;
        CreateLikeRequest requestDto = new LikeFactory(1L).createLikeRequest();
        Like like = new LikeFactory().likeEntity();

        given(likeMapper.toEntity(any(CreateLikeRequest.class), anyLong())).willReturn(like);
        given(likeRepository.save(like)).will(invocation -> {
            CommonTestUtils.setIdField(invocation.getArgument(0), postId);
            return like;
        });
        given(likeMapper.toCreateLikeResponse(any(Like.class))).willReturn(new CreateLikeResponse(postId));

        // ** When **
        CreateLikeResponse responseDto = likeService.createLike(requestDto, 1L);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(postId);
    }

    @Test
    @DisplayName("좋아요 삭제가 성공하면, 삭제된 좋아요 게시글 ID를 반환한다.")
    void shouldReturnLikeIdWhenDeleteLike() throws IllegalAccessException {
        // ** Given **
        Long postId = 1L;
        given(likeMapper.toDeleteLikeResponse(anyLong())).willReturn(new DeleteLikeResponse(postId));

        // ** When **
        DeleteLikeResponse responseDto = likeService.deleteLike(postId, 1L);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(postId);
    }
}