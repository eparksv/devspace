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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import our.portfolio.devspace.common.CommonTestUtils;
import our.portfolio.devspace.common.ControllerTestUtils;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.domain.profile.dto.ProfileCreationDto;
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
    @WithMockUser(username = "1")
    public void createProfile() throws Exception {
        // ** GIVEN **
        // Mock 객체인 ProfileService의 createProfile을 실행하면 requestDto를 반환한다.
        given(profileService.createProfile(anyLong(), any(ProfileCreationDto.class))).willReturn(profileCreationDto());

        // ** WHEN **
        ResultActions resultActions = profileCreationResultActions();

        // ** THEN **
        // HTTP Status Code 201, 응답 본문에는 ResponseEntity로 생성된 프로필 내용이 포함된다.
        HttpResponseBody<ProfileCreationDto> body = new HttpResponseBody<>("프로필이 저장되었습니다.", profileCreationDto());
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
                .requestFields(profileCreationDtoFieldDescriptors())
                .responseSchema(schema("ProfileCreationResponse"))
                .responseFields(
                    new FieldDescriptors(
                        fieldWithPath("message").description("메시지").type(JsonFieldType.STRING))
                        .andWithPrefix("data.", profileCreationDtoFieldDescriptors().getFieldDescriptors().toArray(new FieldDescriptor[0])))
                .build())));
    }

    private ProfileCreationDto profileCreationDto() {
        return ProfileCreationDto.builder()
            .name("이름")
            .introduction("자기소개")
            .jobId(1)
            .build();
    }

    private ResultActions profileCreationResultActions() throws Exception {
        return mockMvc.perform(
            post("/api/profiles")
                .content(CommonTestUtils.valueToString(profileCreationDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", ControllerTestUtils.authorizationToken())
                .with(csrf()));
    }

    private FieldDescriptors profileCreationDtoFieldDescriptors() {
        ConstrainedFields constrainedFields = new ConstrainedFields(ProfileCreationDto.class);
        return new FieldDescriptors(
            constrainedFields.withPath("name").description("사용자 이름").type(JsonFieldType.STRING),
            constrainedFields.withPath("introduction").description("자기 소개").type(JsonFieldType.STRING),
            constrainedFields.withPath("jobId").description("직군 ID").type(JsonFieldType.NUMBER));
    }
}