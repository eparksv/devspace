package our.portfolio.devspace.domain.post.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import our.portfolio.devspace.domain.post.entity.Hashtag;

@DataJpaTest
class HashtagRepositoryTest {

    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    public void findByNameIn() {
        // ** Given **
        List<String> hashtagNames = new ArrayList<>(List.of("#태그1", "#태그2", "#태그3"));
        List<Hashtag> hashtags = List.of(
            new Hashtag("#태그1"),
            new Hashtag("#태그2"),
            new Hashtag("#태그4")
        );
        hashtagRepository.saveAll(hashtags);

        // ** When **
        List<Hashtag> existingHashtags = hashtagRepository.findByNameIn(hashtagNames);

        // ** Then ** hashtagNames에 없는 태그명은 검색되지 않는다.
        Condition<Hashtag> contain = new Condition<>(hashtag -> hashtagNames.contains(hashtag.getName()), "포함");
        Condition<Hashtag> notContain = new Condition<>(hashtag -> hashtag.getName().equals("#태그4"), "불포함");
        assertThat(existingHashtags).have(contain);
        assertThat(existingHashtags).doNotHave(notContain);
    }
}