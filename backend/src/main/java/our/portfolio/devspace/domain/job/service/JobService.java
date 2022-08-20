package our.portfolio.devspace.domain.job.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.domain.job.dto.ListJobsResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.repository.JobRepository;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    @Transactional(readOnly = true)
    public ListJobsResponse listJobs() {
        return ListJobsResponse.from(jobRepository.findAll());
    }

    public Job getJobById(Integer id) {
        return jobRepository.findByIdOrThrow(id);
    }
}
