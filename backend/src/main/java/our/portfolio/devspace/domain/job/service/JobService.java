package our.portfolio.devspace.domain.job.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import our.portfolio.devspace.common.mapper.JobMapper;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.domain.job.repository.JobRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class JobService {

    private final JobMapper jobMapper;
    private final JobRepository jobRepository;

    public List<JobResponse> listJobs(String type) {
        return jobMapper.toJobResponses(jobRepository.findAllByType(JobType.valueOf(type.toUpperCase())));
    }

    public Job getJobById(Integer id) {
        return jobRepository.findByIdOrThrow(id);
    }
}
