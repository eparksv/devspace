package our.portfolio.devspace.domain.follow.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateFollowRequest {
    private Long id;

    @Builder
    public CreateFollowRequest(Long id) {
        this.id = id;
    }
}
