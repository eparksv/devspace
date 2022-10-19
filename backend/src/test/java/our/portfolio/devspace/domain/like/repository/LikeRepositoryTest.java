package our.portfolio.devspace.domain.like.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;

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
}