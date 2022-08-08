package our.portfolio.devspace.domain.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.BaseTimeEntity;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.user.entity.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Profile extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 12)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private String profile_image;
    private String profile_bg_image;

    @Builder
    public Profile(User user, String name, String introduction, Job job, String profile_image,
        String profile_bg_image) {
        this.user = user;
        this.name = name;
        this.introduction = introduction;
        this.job = job;
        this.profile_image = profile_image;
        this.profile_bg_image = profile_bg_image;
    }
}
