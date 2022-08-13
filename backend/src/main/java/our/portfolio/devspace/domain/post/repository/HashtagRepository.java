package our.portfolio.devspace.domain.post.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.post.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findByNameIn(List<String> hashtagName);

}
