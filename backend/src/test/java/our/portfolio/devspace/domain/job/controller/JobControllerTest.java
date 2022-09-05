package our.portfolio.devspace.domain.job.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.SimpleType;
import java.util.List;
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
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.service.JobService;

@AutoConfigureRestDocs
@WebMvcTest(JobController.class)
class JobControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtTokenProvider tokenProvider;

    @MockBean
    JobService jobService;

    @Test
    @WithMockUser(username = "1")
    void listJobs() throws Exception {
        // ** Given **
        List<JobResponse> responseDto = DtoFactory.jobResponses(3);
        given(jobService.listJobs(anyString())).willReturn(responseDto);

        // ** When **
        ResultActions resultActions = mockMvc.perform(get("/api/jobs/{type}", "developer")
            .header("Authorization", ControllerTestUtils.authorizationToken())
            .contentType(MediaType.APPLICATION_JSON)
        );

        // ** Then **
        HttpResponseBody<List<JobResponse>> body = new HttpResponseBody<>("직군 목록이 조회되었습니다.", responseDto);
        resultActions.andExpectAll(
            status().isOk(),
            content().contentType(MediaType.APPLICATION_JSON),
            content().json(CommonTestUtils.valueToString(body))
        );

        // ** API Docs **
        resultActions.andDo(
            document("직군 목록을 성공적으로 조회한다.", resource(ResourceSnippetParameters.builder()
                .summary("직군 목록")
                .tag("Job")
                .pathParameters(parameterWithName("type").type(SimpleType.STRING).description("직군 종류 ex) developer, designer, marketer, planner, startup"))
                .requestHeaders(ControllerTestUtils.authorizationHeader())
                .responseSchema(schema("JobResponses"))
                .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(responseFieldDescriptors()))
                .build())));
    }

    private FieldDescriptors responseFieldDescriptors() {
        return new FieldDescriptors(
            fieldWithPath("[]").type(JsonFieldType.ARRAY).description("직군 목록"),
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("직군 ID"),
            fieldWithPath("[].title").type(JsonFieldType.STRING).description("직군명"));
    }
}
