package our.portfolio.devspace.domain.job.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.JobMapper;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.job.repository.JobRepository;
import our.portfolio.devspace.utils.dummy.DummyJob;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    JobRepository jobRepository;

    @Mock
    JobMapper jobMapper;

    @InjectMocks
    JobService jobService;

    @ParameterizedTest(name = "{0}에 해당하는 직군 목록을 반환한다.")
    @ValueSource(strings = {"developer", "designer", "marketer", "planner", "startup"})
    @DisplayName("직군 목록을 반환한다.")
    void listJobs(String type) throws IllegalAccessException {
        // ** Given **
        List<Job> jobs = DummyJob.jobEntities(JobType.valueOf(type.toUpperCase()));

        given(jobRepository.findAllByType(any(JobType.class))).will(invocation -> DummyJob.jobEntities(invocation.getArgument(0)));
        given(jobMapper.toJobResponses(anyList())).will(invocation -> {
            List<Job> jobsArgument = invocation.getArgument(0);
            return DummyJob.jobResponses(jobsArgument.get(0).getType());
        });

        // ** When **
        List<JobResponse> responseDto = jobService.listJobs(type);

        // ** Then **
        assertThat(responseDto).usingRecursiveComparison().isEqualTo(jobs);
    }
}
