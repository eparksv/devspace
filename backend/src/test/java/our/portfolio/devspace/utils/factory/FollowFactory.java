package our.portfolio.devspace.utils.factory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;

@Getter
@NoArgsConstructor
public class FollowFactory {

    private Long id;

    public FollowFactory(Long id) {
        this.id = id;
    }

    public CreateFollowRequest createFollowRequest() {
        return CreateFollowRequest.builder()
                .id(this.id)
                .build();
    }
}
