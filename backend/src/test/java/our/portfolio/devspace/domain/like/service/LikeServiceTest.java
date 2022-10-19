package our.portfolio.devspace.domain.like.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.repository.LikeRepository;
import our.portfolio.devspace.utils.factory.LikeFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

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
}