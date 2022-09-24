package our.portfolio.devspace.domain.post.repository;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findFirst11BySecret(Boolean secret, Sort sort);

    List<Post> findFirst11ByIdLessThanAndSecret(Long id, Boolean secret, Sort sort);
}
