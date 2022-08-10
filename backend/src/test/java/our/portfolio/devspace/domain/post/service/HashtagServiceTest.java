package our.portfolio.devspace.domain.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.PostHashtag;
import our.portfolio.devspace.domain.post.repository.HashtagRepository;

@ExtendWith(MockitoExtension.class)
class HashtagServiceTest {

    @Mock
    HashtagRepository hashtagRepository;

    @InjectMocks
    HashtagService hashtagService;

    @Test
    public void getHashtagsOfPost() {
        // ** Given **
        List<String> hashtags = new ArrayList<>(List.of("#태그1", "#태그2", "#태그3"));
        List<Hashtag> existingHashtags = new ArrayList<>(List.of(new Hashtag("#태그1"), new Hashtag("#태그2")));
        List<Hashtag> newHashtags = new ArrayList<>(List.of(new Hashtag("#태그1")));

        given(hashtagRepository.findByNameIn(anyList())).willReturn(existingHashtags);
        given(hashtagRepository.saveAll(anyList())).willReturn(newHashtags);

        // ** When ** getHashtagsOfPost를 실행하면
        List<PostHashtag> hashtagsOfPost = hashtagService.getHashtagsOfPost(hashtags);

        // ** Then ** hashtag 필드가 Hashtag 엔티티로 채워진 List<PostHashtag>를 반환한다.
        assertThat(hashtagsOfPost).allSatisfy(hashtag -> {
            assertThat(hashtag.getHashtag()).isNotNull();
        });
    }
}