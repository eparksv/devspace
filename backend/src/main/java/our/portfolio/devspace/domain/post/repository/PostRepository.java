package our.portfolio.devspace.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
