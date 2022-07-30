package our.portfolio.devspace.domain.profile.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ConstrainedFields;
import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProfileService profileService;

    @Value("${security.test.access-token}")
    private String AUTHORIZATION_HEADER;

    @Test
    @WithMockUser(username = "1")
    public void createProfile() throws Exception {
        // ** GIVEN **
        String uri = "/api/profiles";

        // 요청 본문으로 받는 생성할 프로필 내용
        ProfileCreationDto dto = getProfileCreationDto();

        // 응답 본문으로 들어가는 ResponseEntity 내용
        HttpResponseBody<ProfileCreationDto> body = new HttpResponseBody<>("프로필이 저장되었습니다.", dto);

        // Mock 객체인 ProfileService의 createProfile을 실행하면 requestDto를 반환한다.
        given(profileService.createProfile(anyLong(), any(ProfileCreationDto.class))).willReturn(dto);

        // ** WHEN **
        ResultActions resultActions = mockMvc.perform(
            post(uri)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", AUTHORIZATION_HEADER)
                .with(csrf()));

        // ** THEN **

        // ProfileService.createProfile()이 실행되는 것을 검증한다.
        then(profileService).should().createProfile(anyLong(), any(ProfileCreationDto.class));

        // HTTP Status Code 201, 응답 본문에는 ResponseEntity로 생성된 프로필 내용이 포함된다.
        resultActions.andExpectAll(
            status().isCreated(),
            content().json(objectMapper.writeValueAsString(body)));

        // ** API Docs **
        ConstrainedFields constrainedFields = new ConstrainedFields(ProfileCreationDto.class);
        FieldDescriptors fieldsSnippet = new FieldDescriptors(
            constrainedFields.withPath("name").description("사용자 이름"),
            constrainedFields.withPath("introduction").description("자기 소개"),
            constrainedFields.withPath("jobId").description("직군 ID"));

        resultActions.andDo(
            document("Profile", resource(ResourceSnippetParameters.builder()
                .summary("프로필 생성")
                .tag("Profile")
                .requestSchema(schema("ProfileCreationRequest"))
                .requestHeaders(headerWithName("Authorization").description("Access Token"))
                .requestFields(fieldsSnippet)
                .responseSchema(schema("ProfileCreationResponse"))
                .responseFields(
                    new FieldDescriptors(
                        fieldWithPath("message").description("메시지").type(JsonFieldType.STRING))
                        .andWithPrefix("data.", fieldsSnippet.getFieldDescriptors().toArray(new FieldDescriptor[0])))
                .build())));
    }

    private ProfileCreationDto getProfileCreationDto() {
        String name = "이름";
        String introduction = "자기소개";
        Integer jobId = 1;

        return ProfileCreationDto.builder()
            .name(name)
            .introduction(introduction)
            .jobId(jobId)
            .build();
    }
}