package our.portfolio.devspace.domain.job;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.user.User;

@Getter
@NoArgsConstructor
@Table(name = "jobs")
@Entity
public class Job {

    @OneToMany(mappedBy = "job")
    final private List<User> users = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 10)
    private String title;

    @Builder
    public Job(String title) {
        this.title = title;
    }
}