package our.portfolio.devspace.domain.post.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.PostCreationRequestDto;
import our.portfolio.devspace.domain.post.dto.PostCreationResponseDto;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.entity.PostHashtag;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.service.ProfileService;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostMapper postMapper;
    private final ProfileService profileService;
    private final HashtagService hashtagService;
    private final PostRepository postRepository;

    public PostCreationResponseDto createPost(Long userId, PostCreationRequestDto dto) {
        Profile profile = profileService.getProfileById(userId);
        // TODO hashtags null 체크
        List<PostHashtag> hashtags = hashtagService.getHashtagsOfPost(dto.getHashtags());
        Post post = dto.toEntity(profile, hashtags);

        // 저장된 Post를 DTO로 변환하여 반환한다.
        return postMapper.toPostCreationResponseDto(postRepository.save(post));
    }
}
