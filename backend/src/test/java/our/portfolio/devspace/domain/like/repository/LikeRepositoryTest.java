package our.portfolio.devspace.domain.like.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.entity.Like;
import our.portfolio.devspace.utils.factory.LikeFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LikeRepositoryTest {

    @Autowired
    LikeRepository likeRepository;

    @Test
    @DisplayName("id에 해당하는 좋아요 회원을 size 크기만큼 찾아서 반환한다.")
    void findLikeUserByPostId() {

        // ** Given **
        Long id = 2L;
        Pageable pageable = PageRequest.of(0, 3);

        // ** When **
        List<GetLikeResponse> Like = likeRepository.findLikeUserByPostId(id, pageable);

        // ** Then **
        assertThat(Like.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("id에 해당하는 좋아요 회원에 저장한 회원 id가 있는지 확인한다.")
    void conatinLikeUserByPostId() throws IllegalAccessException {

        // ** Given **
        Like like = new LikeFactory().likeEntity();
        Pageable pageable = PageRequest.of(0, 10);

        // ** When **
        likeRepository.save(like);
        List<GetLikeResponse> likes = likeRepository.findLikeUserByPostId(like.getPost().getId(), pageable);

        for (GetLikeResponse getLikeResponse : likes) {
            System.out.println("getLikeResponse = " + getLikeResponse.getId());
        }
        // ** Then **
        Long userId = likes.get(0).getId();
        assertThat(like.getProfile().getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("게시판 id와 유저 id로 좋아요 id 찾고 삭제하기")
    void deleteLikeUserByIds() throws IllegalAccessException {

        // ** Given **
        Like like = new LikeFactory().likeEntity();

        // ** When **
        likeRepository.save(like);
        Long likeId = likeRepository.findIdByProfileIdAndPostId(like.getPost().getId(), like.getProfile().getId());
        likeRepository.deleteById(likeId);

        // ** Then **
        assertThat(likeRepository.findById(likeId)).isEmpty();
    }
}