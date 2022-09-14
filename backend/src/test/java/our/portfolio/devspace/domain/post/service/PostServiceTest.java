package our.portfolio.devspace.domain.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.dto.CreatePostResponse;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.post.repository.PostRepository;
import our.portfolio.devspace.utils.factory.PostFactory;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    PostMapper postMapper;

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Test
    @DisplayName("게시글 등록에 성공하면, 등록된 게시글 ID를 반환한다.")
    void shouldReturnPostIdWhenCreatePost() throws IllegalAccessException {
        // ** Given **
        Long postId = 1L;
        CreatePostRequest requestDto = new PostFactory(1L).createPostRequest();

        given(postMapper.toEntity(anyLong(), any(CreatePostRequest.class))).willReturn(new PostFactory(1L).postEntity());
        given(postMapper.toCreatePostResponse(any(Post.class))).willReturn(new CreatePostResponse(postId));
        given(postRepository.save(any(Post.class))).willReturn(new PostFactory(postId).postEntity());

        // ** When **
        CreatePostResponse responseDto = postService.createPost(1L, requestDto);

        // ** Then **
        assertThat(responseDto.getId()).isEqualTo(postId);
    }

/*    @Test
    void shouldReturnPostsWhenGetRecentPosts() throws IllegalAccessException {
        // ** Given **
        List<Post> posts = EntityFactory.postEntities(5);

        List<PostPreviewResponse> postPreviews = List.of(

        );


        // ** When **
        GetPostsResponse responseDto = postService.getRecentPosts();

        // ** Then **
        assertThat(responseDto.getCount()).isEqualTo(posts.size());
    }*/
}