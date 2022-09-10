package our.portfolio.devspace.common.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.DtoFactory;
import our.portfolio.devspace.common.EntityFactory;
import our.portfolio.devspace.domain.category.entity.Category;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.dto.CreatePostResponse;
import our.portfolio.devspace.domain.post.dto.PostPreviewResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.profile.entity.Profile;

@ExtendWith(MockitoExtension.class)
class PostMapperTest {

    @Mock
    ProfileMapper profileMapper;

    @Mock
    EntityMapper entityMapper;
    @InjectMocks
    PostMapper postMapper;

    @Test
    @DisplayName("게시글 Entity를 게시글 생성 응답 DTO로 매핑하여 반환한다.")
    public void toPostCreationResponseDto() throws IllegalAccessException {
        // ** Given **
        Long postId = 1L;
        Post post = EntityFactory.postEntityWithId(DtoFactory.createPostRequest(1), postId);

        // ** When **
        CreatePostResponse responseDto = postMapper.toCreatePostResponse(post);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("게시글 생성 요청 DTO를 게시글 Entity로 매핑하여 반환한다.")
    public void toEntity() throws IllegalAccessException {
        // ** Given **
        Long userId = 1L;
        CreatePostRequest requestDto = DtoFactory.createPostRequest(1);
        Profile profile = EntityFactory.profileEntityWithId(DtoFactory.createProfileRequest(), 1L);
        Category category = EntityFactory.categoryEntityWithId(requestDto.getCategoryId());

        given(entityMapper.resolve(any(Number.class), any(Class.class))).willAnswer(invocation -> {
            Class<Object> classType = invocation.getArgument(1);
            if (classType.equals(Profile.class)) return profile;
            if (classType.equals(Category.class)) return category;
            return null;
        });

        // ** When **
        Post post = postMapper.toEntity(userId, requestDto);

        // ** Then **
        assertThat(post.getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(post.getContent()).isEqualTo(requestDto.getContent());
        assertThat(post.getSecret()).isEqualTo(requestDto.getSecret());
        assertThat(post.getProfile()).isEqualTo(profile);
        assertThat(post.getHashtags()).allSatisfy(hashtag -> {
            assertThat(requestDto.getHashtags()).contains(hashtag.getName());
            assertThat(hashtag.getPost()).isEqualTo(post);
        });
    }
}