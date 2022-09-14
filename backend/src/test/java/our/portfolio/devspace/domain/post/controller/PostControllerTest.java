package our.portfolio.devspace.domain.post.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.domain.post.dto.CreatePostRequest;
import our.portfolio.devspace.domain.post.dto.CreatePostResponse;
import our.portfolio.devspace.domain.post.service.PostService;
import our.portfolio.devspace.utils.CommonTestUtils;
import our.portfolio.devspace.utils.ControllerTestUtils;
import our.portfolio.devspace.utils.factory.PostFactory;

@AutoConfigureRestDocs
@WebMvcTest(PostController.class)
class PostControllerTest {

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostService postService;

    @Test
    @DisplayName("게시글 생성 요청에 성공하면 HTTP status 201로 응답, 게시글 ID를 반환한다.")
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