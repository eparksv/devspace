package our.portfolio.devspace.domain.like.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.entity.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select new our.portfolio.devspace.domain.like.dto.GetLikeResponse(f.id, f.name, f.image, j.title, f.company) " +
            "from Like l join l.post p join l.profile f join f.job j where p.id = :id order by l.id desc ")
    List<GetLikeResponse> findLikeUserByPostId(@Param(value = "id") Long id, Pageable pageable);

}
