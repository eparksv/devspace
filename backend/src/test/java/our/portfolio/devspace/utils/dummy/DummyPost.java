package our.portfolio.devspace.utils.dummy;

import static our.portfolio.devspace.utils.EntityFactory.setIdField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.Post;

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

    public static List<Post> postEntities(int size) throws IllegalAccessException {
        List<Post> posts = new ArrayList<>();

        for (long i = 1; i <= size; i++) {
            DummyPost post = new DummyPost(i);
            post.setTitle(post.getTitle() + i);
            post.setContent(post.getContent() + i);
            post.setSecret(i % 2 != 0);
            post.setHashtags(List.of("태그" + i, "태그" + i + 1, "태그" + i + 2));
            post.setProfile(new DummyProfile(i));
            posts.add(post.postEntity());
        }

        return posts;
    }

    public Post postEntity() throws IllegalAccessException {
        List<Hashtag> hashtags = this.hashtags.stream().map(Hashtag::new).collect(Collectors.toList());

        Post entity = Post.builder()
            .title(this.title)
            .content(this.content)
            .secret(this.secret)
            .category(this.category.categoryEntity())
            .profile(this.profile.profileEntity())
            .hashtags(hashtags)
            .build();

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
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
