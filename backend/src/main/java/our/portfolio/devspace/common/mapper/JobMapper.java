package our.portfolio.devspace.common.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.Job;

@Mapper
public interface JobMapper {
    List<JobResponse> toJobResponses(List<Job> jobs);
}
