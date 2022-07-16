package our.portfolio.devspace.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.BaseTimeEntity;
import our.portfolio.devspace.domain.job.Job;

@Getter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 12)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false)
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    /*
    @Enumerated(EnumType.STRING)
    private Role role;
    */

    @Builder
    public User(String email, String name, String introduction, String refreshToken, Job job) {
        this.email = email;
        this.name = name;
        this.introduction = introduction;
        this.refreshToken = refreshToken;
        this.job = job;

        job.getUsers().add(this);
    }
}
