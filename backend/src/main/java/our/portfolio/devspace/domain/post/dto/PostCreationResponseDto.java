package our.portfolio.devspace.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import our.portfolio.devspace.domain.post.entity.Post;

@Getter
@AllArgsConstructor
public class PostCreationResponseDto {

    private final Long id;

    public static PostCreationResponseDto from(Post post) {
        return new PostCreationResponseDto(post.getId());
    }
}
