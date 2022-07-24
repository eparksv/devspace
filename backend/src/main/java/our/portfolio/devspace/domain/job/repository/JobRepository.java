package our.portfolio.devspace.domain.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import our.portfolio.devspace.domain.job.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}
