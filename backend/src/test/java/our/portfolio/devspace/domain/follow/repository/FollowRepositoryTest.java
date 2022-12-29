package our.portfolio.devspace.domain.follow.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FollowRepositoryTest {

    @Autowired
    FollowRepository followRepository;

    @Test
    @DisplayName("followerId 와 followeeId가 존재 하지 않을때 성공하는지 확인한다.")
    void NotFollowerIdAndFolloweeId() {
        // ** Given **
        Long follower = 1L;
        Long followee = 2L;

        // ** Then **
        assertThat(followRepository.findIdByFollowerIdAndFolloweeId(follower, followee)).isEmpty();
    }

}