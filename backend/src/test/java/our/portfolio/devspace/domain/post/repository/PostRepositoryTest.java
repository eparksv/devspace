package our.portfolio.devspace.domain.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import our.portfolio.devspace.domain.post.entity.Post;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("포스팅의 PK가 id보다 작고 secret이 false인 포스팅을 PK 내림차순으로 20개 찾는다.")
    void findFirst21ByIdLessThan() {
        // ** Given **
        Long id = 50L;
        Sort sort = Sort.by("id").descending();

        // ** When **
        List<Post> posts = postRepository.findFirst21ByIdLessThanAndSecret(id, false, sort);

        // ** Then **
        assertThat(posts.size()).isEqualTo(21);
        assertThat(posts.get(0).getId()).isLessThan(id);
        assertThat(posts).allMatch(post -> !post.getSecret());
        assertThat(posts).isSortedAccordingTo(Comparator.comparingLong(Post::getId).reversed());
    }

    @Test
    @DisplayName("포스팅의 secret이 false인 포스팅을 PK 내림차순으로 20개 찾는다.")
    void findFirst21BySecret() {
        // ** Given **
        Sort sort = Sort.by("id").descending();

        // ** When **
        List<Post> posts = postRepository.findFirst21BySecret(false, sort);

        // ** Then **
        assertThat(posts.size()).isEqualTo(21);
        assertThat(posts).allMatch(post -> !post.getSecret());
        assertThat(posts).isSortedAccordingTo(Comparator.comparingLong(Post::getId).reversed());
    }
}