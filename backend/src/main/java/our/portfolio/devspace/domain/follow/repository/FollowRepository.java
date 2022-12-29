package our.portfolio.devspace.domain.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import our.portfolio.devspace.domain.follow.entity.Follow;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f where f.follower.id=:followerId and f.followee.id=:followeeId")
    Optional<Follow> findIdByFollowerIdAndFolloweeId(@Param(value = "followerId") Long follower,
                                                     @Param(value = "followeeId") Long followee);

}