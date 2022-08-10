package our.portfolio.devspace.domain.profile.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import our.portfolio.devspace.domain.BaseTimeEntity;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.user.entity.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Profile extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 12)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @OneToMany(mappedBy = "profile")
    private final List<Post> posts = new ArrayList<>();

    private String image;
    private String backgroundImage;

    @Builder
    public Profile(User user, String name, String introduction, Job job, String image,
        String backgroundImage) {
        this.user = user;
        this.name = name;
        this.introduction = introduction;
        this.job = job;
        this.image = image;
        this.backgroundImage = backgroundImage;
    }
}
