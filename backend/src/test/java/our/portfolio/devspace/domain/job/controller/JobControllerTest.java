package our.portfolio.devspace.domain.job.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import java.util.ArrayList;
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
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.domain.job.dto.ListJobsResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
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
        ListJobsResponse responseDto = listJobsResponse();
        given(jobService.listJobs()).willReturn(responseDto);

        // ** When **
        ResultActions resultActions = mockMvc.perform(get("/api/jobs")
            .header("Authorization", ControllerTestUtils.authorizationToken())
            .contentType(MediaType.APPLICATION_JSON)
        );

        // ** Then **
        HttpResponseBody<ListJobsResponse> body = new HttpResponseBody<>("직군 목록이 조회되었습니다.", responseDto);
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
                .requestHeaders(ControllerTestUtils.authorizationHeader())
                .responseSchema(schema("ListJobsResponse"))
                .responseFields(ControllerTestUtils.fieldDescriptorsWithMessage(responseFieldDescriptors()))
                .build())));
    }

    private ListJobsResponse listJobsResponse() {
        List<Job> jobs = new ArrayList<>();
        jobs.add(Job.builder().title("개발1").type(JobType.DEVELOPER).build());
        jobs.add(Job.builder().title("개발2").type(JobType.DEVELOPER).build());
        jobs.add(Job.builder().title("디자인1").type(JobType.DESIGNER).build());
        jobs.add(Job.builder().title("디자인2").type(JobType.DESIGNER).build());
        jobs.add(Job.builder().title("기획1").type(JobType.PLANNER).build());
        jobs.add(Job.builder().title("기획2").type(JobType.PLANNER).build());
        jobs.add(Job.builder().title("마케팅1").type(JobType.MARKETER).build());
        jobs.add(Job.builder().title("마케팅2").type(JobType.MARKETER).build());
        jobs.add(Job.builder().title("기업1").type(JobType.COMPANY).build());
        jobs.add(Job.builder().title("기업2").type(JobType.COMPANY).build());
        return ListJobsResponse.from(jobs);
    }

    private FieldDescriptors responseFieldDescriptors() {
        return new FieldDescriptors(
            fieldWithPath("developers.[]").type(JsonFieldType.ARRAY).description("개발자 직군 목록"),
            fieldWithPath("developers.[].id").type(JsonFieldType.NUMBER).description("개발자 직군 ID"),
            fieldWithPath("developers.[].name").type(JsonFieldType.STRING).description("개발자 직군명"),
            fieldWithPath("designers.[]").type(JsonFieldType.ARRAY).description("디자이너 직군 목록"),
            fieldWithPath("designers.[].id").type(JsonFieldType.NUMBER).description("디자이너 직군 ID"),
            fieldWithPath("designers.[].name").type(JsonFieldType.STRING).description("디자이너 직군명"),
            fieldWithPath("planners.[]").type(JsonFieldType.ARRAY).description("기획자 직군 목록"),
            fieldWithPath("planners.[].id").type(JsonFieldType.NUMBER).description("기획자 직군 ID"),
            fieldWithPath("planners.[].name").type(JsonFieldType.STRING).description("기획자 직군명"),
            fieldWithPath("marketers.[]").type(JsonFieldType.ARRAY).description("마케터 직군 목록"),
            fieldWithPath("marketers.[].id").type(JsonFieldType.NUMBER).description("마케터 직군 ID"),
            fieldWithPath("marketers.[].name").type(JsonFieldType.STRING).description("마케터 직군명"),
            fieldWithPath("companies.[]").type(JsonFieldType.ARRAY).description("창업 직군 목록"),
            fieldWithPath("companies.[].id").type(JsonFieldType.NUMBER).description("창업 직군 ID"),
            fieldWithPath("companies.[].name").type(JsonFieldType.STRING).description("창업 직군명"));
    }
}