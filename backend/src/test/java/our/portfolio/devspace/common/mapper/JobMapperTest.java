package our.portfolio.devspace.common.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.utils.dummy.DummyJob;

@ExtendWith(SpringExtension.class)
@Import(JobMapperImpl.class)
class JobMapperTest {

    @Autowired
    JobMapper jobMapper;

    @Test
    void toListJobsResponse() throws IllegalAccessException {
        // ** Given **
        List<Job> jobs = DummyJob.jobEntities(JobType.DEVELOPER);

        // ** When **
        List<JobResponse> responseDto = jobMapper.toJobResponses(jobs);

        // ** Then **
        assertThat(responseDto).usingRecursiveComparison().isEqualTo(jobs);
    }
}