package our.portfolio.devspace.domain.user.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.BaseTimeEntity;
import our.portfolio.devspace.domain.follow.entity.Follow;
import our.portfolio.devspace.domain.like.entity.Like;
import our.portfolio.devspace.domain.profile.entity.Profile;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private OAuth2Provider provider;

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Setter(AccessLevel.PACKAGE)
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.PERSIST)
    private final List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "followee", cascade = CascadeType.PERSIST)
    private final List<Follow> followees = new ArrayList<>();

    @Builder
    public User(String subject, OAuth2Provider provider, Role role) {
        this.subject = subject;
        this.provider = provider;
        this.role = role;
    }
}
