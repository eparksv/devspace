package our.portfolio.devspace.utils.dummy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.domain.job.entity.JobType;

@Setter
@Getter
@NoArgsConstructor
public class DummyJob {

    private Integer id;
    private JobType type = JobType.DEVELOPER;
    private String title = "개발자";

    public DummyJob(Integer id) {
        this.id = id;
    }
}
