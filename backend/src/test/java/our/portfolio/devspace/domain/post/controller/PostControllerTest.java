package our.portfolio.devspace.domain.post.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ConstrainedFields;
import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.dto.CreatePostResponse;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery.PostFilter;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery.PostSort;
import our.portfolio.devspace.domain.post.dto.GetPostsResponse;
import our.portfolio.devspace.domain.post.service.PostService;
import our.portfolio.devspace.domain.post.service.pagination.PostPaginationService;
import our.portfolio.devspace.domain.post.service.pagination.PostPaginationServiceFactory;
import our.portfolio.devspace.utils.CommonTestUtils;
import our.portfolio.devspace.utils.ControllerTestUtils;
import our.portfolio.devspace.utils.ControllerTestUtils.WebSecurityTestConfiguration;
import our.portfolio.devspace.utils.factory.PostFactory;

@AutoConfigureRestDocs
@WebMvcTest(PostController.class)
@Import(WebSecurityTestConfiguration.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @MockBean
    PostPaginationServiceFactory factory;

    @MockBean
    PostPaginationService service;

    @Test
    @DisplayName("게시글 생성 요청에 성공하면 HTTP status 201으로 응답, 게시글 ID를 반환한다.")
    @WithMockUser(username = "1")
    public void createPost() throws Exception {
        // ** Given **
        // Mock 객체인 PostService의 createPost를 실행하면 requestDto를 반환한다.
        CreatePostResponse responseDto = new CreatePostResponse(1L);
        given(postService.createPost(anyLong(), any(CreatePostRequest.class))).willReturn(responseDto);

        // ** When **
        ResultActions resultActions = postCreationResultActions();

        // ** Then **
        // HTTP Status Code 201, 응답 본문에는 ResponseEntity로 생성된 포스트 ID가 포함된다.
        HttpResponseBody<CreatePostResponse> body = new HttpResponseBody<>("등록되었습니다.", responseDto);
        resultActions.andExpectAll(
            status().isCreated(),
            content().json(CommonTestUtils.valueToString(body))
        );

        // ** API Docs **
        resultActions.andDo(
            document("포스트를 성공적으로 생성한다", resource(ResourceSnippetParameters.builder()
                .summary("포스트 생성")
                .tag("Post")
                .requestSchema(schema("PostCreationRequest"))
                .requestHeaders(
                    ControllerTestUtils.authorizationHeader(),
                    ControllerTestUtils.contentTypeApplicationJsonHeader())
                .requestFields(postCreationDescriptors())
                .responseSchema(schema("PostCreationResponse"))
                .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(
                    new FieldDescriptors(fieldWithPath("id").description("생성한 포스트 ID").type(JsonFieldType.NUMBER))))
                .build())));
    }

    @Test
    @DisplayName("포스팅 목록 요청에 성공하면 HTTP status 200으로 응답, 포스팅 목록을 반환한다.")
    public void getPostsShouldReturnResponseEntityWithGetPostsResponse() throws Exception {
        // ** Given **
        GetPostsQuery query = new GetPostsQuery();
        query.setSort(PostSort.RECENT);
        query.setFilter(PostFilter.NONE);

        GetPostsResponse responseDto = new PostFactory().getPostsResponse(query, 10);

        given(factory.getService(any(PostFilter.class))).willReturn(service);
        given(service.getPosts(any(GetPostsQuery.class))).willReturn(responseDto);

        // ** When **
        ResultActions resultActions = mockMvc.perform(
            get("/api/posts")
        );

        // ** Then **
        HttpResponseBody<GetPostsResponse> body = new HttpResponseBody<>("조회되었습니다.", responseDto);
        resultActions.andExpectAll(
            status().isOk(),
            content().json(CommonTestUtils.valueToString(body))
        );

        // ** API Docs **
        resultActions.andDo(
            document("최신순 포스팅 목록을 조회한다.", resource(ResourceSnippetParameters.builder()
                .tag("Post")
                .requestSchema(schema("GetPostsQuery"))
                .requestParameters(
                    parameterWithName("sort").description("정렬: recent").optional(),
                    parameterWithName("filter").description("필터: none").optional()
                )
                .responseSchema(schema("GetPostsResponse"))
                .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(
                        new FieldDescriptors(
                            fieldWithPath("count").description("검색된 포스팅의 개수").type(JsonFieldType.NUMBER),
                            fieldWithPath("nextRequestUri").description("다음 페이지 요청 URI").type(JsonFieldType.STRING),
                            fieldWithPath("posts").description("검색된 포스팅 목록").type(JsonFieldType.ARRAY),
                            fieldWithPath("posts[].id").description("포스팅 ID").type(JsonFieldType.NUMBER),
                            fieldWithPath("posts[].profile").description("작성자 프로필").type(JsonFieldType.OBJECT),
                            fieldWithPath("posts[].profile.id").description("작성자 프로필 ID").type(JsonFieldType.NUMBER),
                            fieldWithPath("posts[].profile.name").description("작성자 이름").type(JsonFieldType.STRING),
                            fieldWithPath("posts[].profile.job").description("작성자 직종").type(JsonFieldType.STRING),
                            fieldWithPath("posts[].profile.company").description("작성자 회사").type(JsonFieldType.STRING),
                            fieldWithPath("posts[].profile.image").description("작성자 프로필 이미지").type(JsonFieldType.STRING).optional(),
                            fieldWithPath("posts[].createdDate").description("포스팅 작성시간").type(JsonFieldType.STRING),
                            fieldWithPath("posts[].hashtags").description("포스팅 해시태그").type(JsonFieldType.ARRAY),
                            fieldWithPath("posts[].title").description("포스팅 제목").type(JsonFieldType.STRING),
                            fieldWithPath("posts[].content").description("포스팅 내용").type(JsonFieldType.STRING),
                            fieldWithPath("posts[].likeCount").description("포스팅 좋아요 수").type(JsonFieldType.NUMBER),
                            fieldWithPath("posts[].commentCount").description("포스팅 댓글 수").type(JsonFieldType.NUMBER)
                        )
                    )
                )
                .build()
            ))
        );
    }

    private ResultActions postCreationResultActions() throws Exception {
        return mockMvc.perform(
            post("/api/posts")
                .content(CommonTestUtils.valueToString(new PostFactory(1L).createPostRequest()))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", ControllerTestUtils.authorizationToken())
                .with(csrf())
        );
    }

    private FieldDescriptors postCreationDescriptors() {
        ConstrainedFields postCreationDtoField = new ConstrainedFields(CreatePostRequest.class);
        return new FieldDescriptors(
            postCreationDtoField.withPath("title").description("포스트 제목").type(JsonFieldType.STRING),
            postCreationDtoField.withPath("content").description("포스트 본문").type(JsonFieldType.STRING),
            postCreationDtoField.withPath("secret").description("나만 보기 설정").type(JsonFieldType.BOOLEAN),
            postCreationDtoField.withPath("hashtags").description("해시태그 목록").type(JsonFieldType.ARRAY),
            postCreationDtoField.withPath("categoryId").description("카테고리 ID").type(JsonFieldType.NUMBER)
        );
    }
}