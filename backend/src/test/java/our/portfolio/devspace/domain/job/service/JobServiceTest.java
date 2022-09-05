package our.portfolio.devspace.domain.job.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.DtoFactory;
import our.portfolio.devspace.common.EntityFactory;
import our.portfolio.devspace.common.mapper.JobMapper;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.job.repository.JobRepository;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    JobRepository jobRepository;

    @Mock
    JobMapper jobMapper;

    @InjectMocks
    JobService jobService;

    @Test
    @DisplayName("type에 해당하는 직군 목록을 반환한다.")
    void listJobs() throws IllegalAccessException {
        // ** Given **
        String type = "developer";
        List<Job> jobs = EntityFactory.jobEntities("개발자", 3);

        given(jobRepository.findAllByType(any(JobType.class))).willReturn(jobs);
        given(jobMapper.toJobResponses(anyList())).willReturn(DtoFactory.jobResponses(3));

        // ** When **
        List<JobResponse> responseDto = jobService.listJobs(type);

        // ** Then **
        assertThat(responseDto).usingRecursiveComparison().isEqualTo(jobs);
    }
}
