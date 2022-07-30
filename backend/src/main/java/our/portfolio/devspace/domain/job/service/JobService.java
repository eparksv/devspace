package our.portfolio.devspace.domain.job.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.repository.JobRepository;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Job getJobById(Integer id) {
        return jobRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Job ID 입니다."));
    }
}
