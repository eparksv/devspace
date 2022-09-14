package our.portfolio.devspace.utils.factory;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.domain.job.dto.JobResponse;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;

@Setter
@Getter
@NoArgsConstructor
public class JobFactory {

    private Integer id;
    private JobType type = JobType.DEVELOPER;
    private String title = "개발자";

    public JobFactory(Integer id) {
        this.id = id;
    }

    public static List<JobResponse> jobResponses(JobType type) {
        List<JobResponse> jobResponses = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            JobFactory job = new JobFactory(i);
            job.setType(type);
            job.setTitle(type.toString() + i);
            jobResponses.add(new JobResponse(i, job.getTitle()));
        }

        return jobResponses;
    }

    public static List<Job> jobEntities(JobType type) throws IllegalAccessException {
        List<Job> entities = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            JobFactory job = new JobFactory(i);
            job.setType(type);
            job.setTitle(type.toString() + i);
            entities.add(job.jobEntity());
        }

        return entities;
    }

    public Job jobEntity() throws IllegalAccessException {
        Job entity = new Job(this.type, this.title);

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }
}
