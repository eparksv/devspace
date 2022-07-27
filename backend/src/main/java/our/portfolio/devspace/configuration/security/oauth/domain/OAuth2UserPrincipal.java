package our.portfolio.devspace.configuration.security.oauth.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import our.portfolio.devspace.domain.user.entity.Role;
import our.portfolio.devspace.domain.user.entity.User;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class OAuth2UserPrincipal implements OAuth2User, UserDetails {

    @Getter
    private final Long id;
    private final String username;
    private final Role role;
    private final Collection<GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static OAuth2UserPrincipal from(User user) {
        return new OAuth2UserPrincipal(
            user.getId(),
            user.getName(),
            user.getRole(),
            Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getCode()))
        );
    }

    public static OAuth2UserPrincipal from(User user, Map<String, Object> attributes) {
        OAuth2UserPrincipal userPrincipal = from(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
