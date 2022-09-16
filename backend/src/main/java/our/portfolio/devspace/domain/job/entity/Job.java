package our.portfolio.devspace.domain.job.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.profile.entity.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jobs")
@Entity
public class Job {

    @OneToMany(mappedBy = "job")
    private final List<Profile> profiles = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 10)
    private String title;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private JobType type;

    @Builder
    public Job(JobType type, String title) {
        this.title = title;
        this.type = type;
    }
}
