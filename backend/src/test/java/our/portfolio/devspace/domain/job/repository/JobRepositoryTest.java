package our.portfolio.devspace.domain.job.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;

@DataJpaTest
class JobRepositoryTest {

    @Autowired
    JobRepository jobRepository;

    @Test
    @DisplayName("JobType에 해당하는 직군을 모두 찾아서 반환한다.")
    void findAllByType() {
        // ** Given **
        JobType jobType = JobType.DEVELOPER;

        // ** When **
        List<Job> jobs =  jobRepository.findAllByType(jobType);

        // ** Then **
        assertThat(jobs.size()).isEqualTo(9); //data.sql로 저장되는 개발자 직군의 개수
    }
}