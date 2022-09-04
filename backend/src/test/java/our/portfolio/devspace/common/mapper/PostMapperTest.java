package our.portfolio.devspace.common.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import our.portfolio.devspace.common.DtoFactory;
import our.portfolio.devspace.common.EntityFactory;
import our.portfolio.devspace.domain.post.dto.PostCreationRequestDto;
import our.portfolio.devspace.domain.post.dto.PostCreationResponseDto;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.entity.PostHashtag;
import our.portfolio.devspace.domain.post.service.HashtagService;
import our.portfolio.devspace.domain.profile.entity.Profile;

@Import({PostMapperImpl.class})
@ExtendWith(SpringExtension.class)
class PostMapperTest {

    @MockBean
    EntityMapper entityMapper;

    @MockBean
    HashtagService hashtagService;

    @Autowired
    PostMapperImpl postMapper;

    @Test
    @DisplayName("게시글 Entity를 게시글 생성 응답 DTO로 매핑하여 반환한다.")
    public void toPostCreationResponseDto() throws IllegalAccessException {
        // ** Given **
        Long postId = 1L;
        Post post = EntityFactory.postEntityWithId(DtoFactory.postCreationRequestDto(), postId);

        // ** When **
        PostCreationResponseDto responseDto = postMapper.toPostCreationResponseDto(post);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("게시글 생성 요청 DTO를 게시글 Entity로 매핑하여 반환한다.")
    public void toEntity() throws IllegalAccessException {
        // ** Given **
        Long userId = 1L;
        PostCreationRequestDto requestDto = DtoFactory.postCreationRequestDto();
        Profile profile = EntityFactory.profileEntityWithId(DtoFactory.createProfileRequest(), 1L);
        List<PostHashtag> postHashtags = requestDto.getHashtags().stream()
            .map(EntityFactory::postHashtag)
            .collect(Collectors.toList());

        given(entityMapper.resolve(anyLong(), any(Class.class))).willReturn(profile);
        given(hashtagService.getHashtagsOfPost(anyList())).willReturn(postHashtags);

        // ** When **
        Post post = postMapper.toEntity(userId, requestDto);

        // ** Then **
        assertThat(post.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(post.getContent()).isEqualTo(requestDto.getContent());
        assertThat(post.getSecret()).isEqualTo(requestDto.getSecret());
        assertThat(post.getProfile()).isEqualTo(profile);
        assertThat(post.getHashtagsOfPost()).allSatisfy(postHashtag -> {
            assertThat(requestDto.getHashtags()).contains(postHashtag.getHashtag().getName());
            assertThat(postHashtag.getPost()).isEqualTo(post);
        });
    }
}