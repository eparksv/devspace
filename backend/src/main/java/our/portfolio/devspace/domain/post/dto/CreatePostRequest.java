package our.portfolio.devspace.domain.post.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

    private String title;

    @NotBlank
    @Length(max = 16383, message = "본문 내용은 16,383자 이하로 입력하세요.") // MYSQL TEXT 타입 최대 65,535 byte / 4 (utf-8 한글자당 최대 4byte)
    private String content;

    @NotNull
    private Boolean secret;

    @Size(max = 3)
    private List<String> hashtags;

    @NotNull
    private Integer categoryId;

    @Builder
    public CreatePostRequest(String title, String content, Boolean secret, List<String> hashtags, Integer categoryId) {
        this.title = title;
        this.content = content;
        this.secret = secret;
        this.hashtags = hashtags;
        this.categoryId = categoryId;
    }
}
