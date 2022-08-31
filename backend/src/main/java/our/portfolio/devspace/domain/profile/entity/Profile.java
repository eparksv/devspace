package our.portfolio.devspace.domain.profile.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "profiles")
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

    @Column(nullable = false, length = 255)
    private String introduction;

    @Column(length = 30)
    private String company;

    @Column(length = 30)
    private String career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @OneToMany(mappedBy = "profile")
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "profile", cascade = CascadeType.PERSIST)
    private List<ReferenceLink> referenceLinks;

    private String image;
    private String backgroundImage;

    @Builder
    public Profile(User user, String name, String introduction, String company, String career, Job job, List<ReferenceLink> referenceLinks, String image, String backgroundImage) {
        this.user = user;
        this.name = name;
        this.introduction = introduction;
        this.company = company;
        this.career = career;
        this.job = job;
        this.referenceLinks = referenceLinks;
        this.image = image;
        this.backgroundImage = backgroundImage;

        // Job, ReferenceLink Entity에 Profile 매핑
        this.job.getProfiles().add(this);
        this.referenceLinks.forEach(referenceLink -> referenceLink.setProfile(this));
    }
}
