package our.portfolio.devspace.utils.factory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;
import our.portfolio.devspace.domain.follow.entity.Follow;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

@Getter
@NoArgsConstructor
public class FollowFactory {

    private Long id;
    private UserFactory follower;
    private UserFactory followee;

    public FollowFactory(Long id) {
        this.id = id;
    }

    public FollowFactory(Long id, Long followerId, Long followeeId) {
        this.id = id;
        this.follower = new UserFactory(followerId);
        this.followee = new UserFactory(followeeId);
    }

    public Follow followEntity() throws IllegalAccessException {

        Follow entity = Follow.builder()
                .id(this.id)
                .follower(this.follower.userEntity())
                .followee(this.followee.userEntity())
                .build();

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }

    public CreateFollowRequest createFollowRequest() {
        return CreateFollowRequest.builder()
                .id(this.id)
                .build();
    }
}
