package our.portfolio.devspace.domain.follow.controller;

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
import our.portfolio.devspace.domain.follow.dto.CreateFollowRequest;
import our.portfolio.devspace.domain.follow.dto.CreateFollowResponse;
import our.portfolio.devspace.domain.follow.service.FollowService;
import our.portfolio.devspace.utils.CommonTestUtils;
import our.portfolio.devspace.utils.ControllerTestUtils;
import our.portfolio.devspace.utils.factory.FollowFactory;

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

@AutoConfigureRestDocs
@WebMvcTest(FollowController.class) //컨트롤러 테스트 설정
@Import(ControllerTestUtils.WebSecurityTestConfiguration.class) //API 권한 설정
class FollowControllerTest {

    @Autowired
    MockMvc mockMvc; //API 요청 객체

    @MockBean
    FollowService followService;

    @Test
    @DisplayName("팔로우 생성 요청에 성공하면 HTTP status 201으로 응답, followee ID를 반환한다.")
    @WithMockUser(username = "1")
    void createFollow() throws Exception {
        // ** Given **
        CreateFollowResponse responseDto = new CreateFollowResponse(1L);
        given(followService.createFollow(any(CreateFollowRequest.class), anyLong())).willReturn(responseDto);


        // ** When **
        ResultActions resultActions = followCreationResultActions();

        // ** Then **
        HttpResponseBody<CreateFollowResponse> body = new HttpResponseBody<>("등록되었습니다.", responseDto);
        resultActions.andExpectAll(
                status().isCreated(),
                content().json(CommonTestUtils.valueToString(body))
        );

        // ** API Docs **
        resultActions.andDo(
                document("팔로우를 성공적으로 생성한다", resource(ResourceSnippetParameters.builder()
                        .summary("팔로우 생성")
                        .tag("Follow")
                        .requestSchema(schema("FollowCreatRequest"))
                        .requestHeaders(
                                ControllerTestUtils.authorizationHeader(),
                                ControllerTestUtils.contentTypeApplicationJsonHeader())
                        .requestFields(followCreationDescriptors())
                        .responseSchema(schema("FollowCreationResponse"))
                        .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(
                                new FieldDescriptors(fieldWithPath("id").description("팔로우한 유저 ID").type(JsonFieldType.NUMBER))))
                        .build())));

    }

    private FieldDescriptors followCreationDescriptors() {
        ConstrainedFields followCreationDtoField = new ConstrainedFields(CreateFollowRequest.class);
        return new FieldDescriptors(
                followCreationDtoField.withPath("id").type(JsonFieldType.NUMBER).description("팔로우한 유저 ID")
        );
    }

    private ResultActions followCreationResultActions() throws Exception {
        return mockMvc.perform(
                post("/api/follow")
                        .content(CommonTestUtils.valueToString(new FollowFactory(1L).createFollowRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", ControllerTestUtils.authorizationToken())
                        .with(csrf())
        );
    }

}