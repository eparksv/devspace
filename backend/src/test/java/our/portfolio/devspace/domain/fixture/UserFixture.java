package our.portfolio.devspace.domain.fixture;

import org.springframework.security.access.vote.RoleVoter;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.user.entity.Role;
import our.portfolio.devspace.domain.user.entity.User;

public class UserFixture {
    public static final String PROVIDER_SUBJECT = "1234567890";
    public static final OAuth2Provider GOOGLE_PROVIDER = OAuth2Provider.GOOGLE;

    public static User createUser() {
        return User.builder()
            .subject(PROVIDER_SUBJECT)
            .provider(GOOGLE_PROVIDER)
            .role(Role.USER)
            .build();
    }
}
