package our.portfolio.devspace.domain.job.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findAllByType(JobType type);

    default Job findByIdOrThrow(Integer id) {
        return findById(id).orElseThrow(() -> new CustomException("Job Id " + id + "에 해당하는 직군이 없습니다.", ErrorDetail.JOB_NOT_FOUND));
    }

}
