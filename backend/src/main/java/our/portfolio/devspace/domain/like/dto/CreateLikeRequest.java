package our.portfolio.devspace.domain.like.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateLikeRequest {
    private Long id;

    @Builder
    public CreateLikeRequest(Long id) {
        this.id = id;
    }
}
