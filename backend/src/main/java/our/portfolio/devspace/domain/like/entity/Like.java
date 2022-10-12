package our.portfolio.devspace.domain.like.entity;

import lombok.*;
import our.portfolio.devspace.domain.post.entity.Post;
import our.portfolio.devspace.domain.profile.entity.Profile;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public Like(Long id, Profile profile, Post post) {
        this.id = id;
        this.profile = profile;
        this.post = post;
    }
}
