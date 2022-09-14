package our.portfolio.devspace.utils.dummy;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;

@Setter
@Getter
@NoArgsConstructor
public class DummyPost {

    private Long id;
    private String title = "제목";
    private String content = "내용";
    private List<String> hashtags = List.of("태그1", "태그2", "태그3");
    private boolean secret = false;
    private DummyCategory category = new DummyCategory(1);
    private DummyProfile profile = new DummyProfile(1L);

    public DummyPost(Long id) {
        this.id = id;
    }

    public CreatePostRequest createPostRequest() {
        return CreatePostRequest.builder()
            .title(this.title)
            .content(this.content)
            .secret(this.secret)
            .categoryId(this.category.getId())
            .hashtags(this.hashtags)
            .build();
    }
}
