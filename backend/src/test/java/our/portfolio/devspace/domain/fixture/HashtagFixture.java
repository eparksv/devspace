package our.portfolio.devspace.domain.fixture;

import java.util.List;
import java.util.stream.Collectors;
import our.portfolio.devspace.domain.post.entity.Hashtag;

public class HashtagFixture {
    public static final List<String> HASHTAGS = List.of("해시태그1", "해시태그2", "해시태그3");

    public static List<Hashtag> createHashtags() {
        return HASHTAGS.stream()
            .map(Hashtag::new)
            .collect(Collectors.toList());
    }
}
