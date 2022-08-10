package our.portfolio.devspace.domain.post.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.entity.PostHashtag;
import our.portfolio.devspace.domain.profile.entity.Profile;

@Getter
public class PostCreationRequestDto {

    private final String title;

    @NotBlank
    @Length(max = 16383, message = "본문 내용은 16,383자 이하로 입력하세요.") // MYSQL TEXT 타입 최대 65,535 byte / 4 (utf-8 한글자당 최대 4byte)
    private final String content;

    @NotNull
    private final Boolean secret;

    @Size(max = 10)
    private final List<String> hashtags;

    @Builder
    public PostCreationRequestDto(String title, String content, Boolean secret, List<String> hashtags) {
        this.title = title;
        this.content = content;
        this.secret = secret;
        this.hashtags = hashtags;
    }

    public Post toEntity(Profile profile, List<PostHashtag> hashtags) {
        return Post.builder()
            .profile(profile)
            .title(this.title)
            .content(this.content)
            .secret(this.secret)
            .hashtagsOfPost(hashtags)
            .build();
    }
}
