package our.portfolio.devspace.domain.post.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.domain.post.entity.Hashtag;
import our.portfolio.devspace.domain.post.entity.PostHashtag;
import our.portfolio.devspace.domain.post.repository.HashtagRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public List<PostHashtag> getHashtagsOfPost(List<String> hashtagNames) {
        List<Hashtag> hashtags = getOrCreateHashtags(hashtagNames);
        // DB에 등록된 해시태그를 PostHashtag에 세팅한 후 반환한다.
        return hashtags.stream().map(PostHashtag::new).collect(Collectors.toList());
    }

    // DB에서 해시태그를 가지고 오고, DB에 없으면 등록한 후 가지고 온다.
    private List<Hashtag> getOrCreateHashtags(List<String> hashtagNames) {
        List<Hashtag> existingHashtags = getHashtagsByNameIn(hashtagNames);
        removeExistingHashtag(hashtagNames, existingHashtags);
        List<Hashtag> newHashtag = createHashtags(hashtagNames);
        newHashtag.addAll(existingHashtags);
        return newHashtag;
    }

    // hashtagNames에 포함된 태그명으로 DB에 검색한다.
    private List<Hashtag> getHashtagsByNameIn(List<String> hashtagNames) {
        return hashtagRepository.findByNameIn(hashtagNames);
    }

    // hashtagNames 중 DB에 이미 존재하는 해시태그를 제거한다.
    private void removeExistingHashtag(List<String> hashtagNames, List<Hashtag> existingHashtags) {
        existingHashtags.forEach(hashtag -> hashtagNames.remove(hashtag.getName()));
    }

    // hashtagNames를 Hashtag 엔티티로 매핑하여 DB에 등록한다.
    private List<Hashtag> createHashtags(List<String> hashtagNames) {
        return hashtagRepository.saveAll(
            hashtagNames.stream().map(Hashtag::new).collect(Collectors.toList())
        );
    }

}
