package our.portfolio.devspace.domain.profile.controller;

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
import our.portfolio.devspace.common.CommonTestUtils;
import our.portfolio.devspace.common.ControllerTestUtils;
import our.portfolio.devspace.common.DtoFactory;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest;
import our.portfolio.devspace.domain.profile.dto.CreateProfileRequest.ReferenceLinkDto;
import our.portfolio.devspace.domain.profile.dto.CreateProfileResponse;
import our.portfolio.devspace.domain.profile.service.ProfileService;

@AutoConfigureRestDocs
@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @MockBean
    private JwtTokenProvider tokenProvider;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Test
    @DisplayName("프로필 생성 요청에 성공하면 HTTP status 201로 응답, 프로필 ID를 반환한다.")
    @WithMockUser(username = "1")
    public void createProfile() throws Exception {
        // ** GIVEN **
        // Mock 객체인 ProfileService의 createProfile을 실행하면 requestDto를 반환한다.
        CreateProfileResponse responseDto = new CreateProfileResponse(1L);
        given(profileService.createProfile(anyLong(), any(CreateProfileRequest.class))).willReturn(responseDto);

        // ** WHEN **
        ResultActions resultActions = profileCreationResultActions();

        // ** THEN **
        // HTTP Status Code 201, 응답 본문에는 ResponseEntity로 생성된 프로필 내용이 포함된다.
        HttpResponseBody<CreateProfileResponse> body = new HttpResponseBody<>("프로필이 저장되었습니다.", responseDto);
        resultActions.andExpectAll(
            status().isCreated(),
            content().json(CommonTestUtils.valueToString(body)));

        // ** API Docs **
        resultActions.andDo(
            document("프로필을 성공적으로 생성한다", resource(ResourceSnippetParameters.builder()
                .summary("프로필 생성")
                .tag("Profile")
                .requestSchema(schema("ProfileCreationRequest"))
                .requestHeaders(
                    ControllerTestUtils.authorizationHeader(),
                    ControllerTestUtils.contentTypeApplicationJsonHeader())
                .requestFields(profileCreationDescriptors())
                .responseSchema(schema("ProfileCreationResponse"))
                .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(
                    new FieldDescriptors(fieldWithPath("id").description("생성한 프로필 ID").type(JsonFieldType.NUMBER)))
                )
                .build())));
    }

    private ResultActions profileCreationResultActions() throws Exception {
        return mockMvc.perform(
            post("/api/profiles")
                .content(CommonTestUtils.valueToString(DtoFactory.createProfileRequest()))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", ControllerTestUtils.authorizationToken())
                .with(csrf()));
    }

    private FieldDescriptors profileCreationDescriptors() {
        ConstrainedFields profileConstrainedFields = new ConstrainedFields(CreateProfileRequest.class);
        ConstrainedFields linkConstrainedFields = new ConstrainedFields(ReferenceLinkDto.class);

        return new FieldDescriptors(
            profileConstrainedFields.withPath("name").description("사용자 이름").type(JsonFieldType.STRING),
            profileConstrainedFields.withPath("introduction").description("자기 소개").type(JsonFieldType.STRING),
            profileConstrainedFields.withPath("jobId").description("직군 ID").type(JsonFieldType.NUMBER),
            profileConstrainedFields.withPath("company").description("회사명").type(JsonFieldType.STRING),
            profileConstrainedFields.withPath("career").description("경력 기간").type(JsonFieldType.STRING),
            profileConstrainedFields.withPath("referenceLinks[]").description("링크 목록").type(JsonFieldType.ARRAY),
            linkConstrainedFields.withPath("referenceLinks[].title").description("링크 이름").type(JsonFieldType.STRING),
            linkConstrainedFields.withPath("referenceLinks[].url").description("링크 URL").type(JsonFieldType.STRING)
        );
    }
}