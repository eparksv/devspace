package our.portfolio.devspace.utils.dummy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.user.entity.Role;

@Setter
@Getter
@NoArgsConstructor
public class DummyUser {

    private Long id;
    private Role role = Role.USER;
    private String subject = "12345";
    private OAuth2Provider provider = OAuth2Provider.GOOGLE;

    public DummyUser(Long id) {
        this.id = id;
    }

}
