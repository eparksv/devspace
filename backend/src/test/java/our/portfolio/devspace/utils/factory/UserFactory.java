package our.portfolio.devspace.utils.factory;

import static our.portfolio.devspace.utils.CommonTestUtils.setIdField;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2Provider;
import our.portfolio.devspace.domain.user.entity.Role;
import our.portfolio.devspace.domain.user.entity.User;

@Setter
@Getter
@NoArgsConstructor
public class UserFactory {

    private Long id;
    private Role role = Role.USER;
    private String subject = "12345";
    private OAuth2Provider provider = OAuth2Provider.GOOGLE;

    public UserFactory(Long id) {
        this.id = id;
    }

    public User userEntity() throws IllegalAccessException {
        User entity = User.builder()
            .role(this.role)
            .subject(this.subject)
            .provider(this.provider)
            .build();

        if (this.id != null) {
            setIdField(entity, this.id);
        }

        return entity;
    }

}
