package our.portfolio.devspace.configuration.security.oauth.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.configuration.security.oauth.domain.OAuth2UserPrincipal;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2UserInfo;
import our.portfolio.devspace.configuration.security.oauth.userinfo.OAuth2UserInfoFactory;
import our.portfolio.devspace.domain.user.entity.Role;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.of(registrationId, oAuth2User.getAttributes());

        Optional<User> foundUser = userRepository.findByEmail(userInfo.getEmail());
        if (foundUser.isEmpty()) {
            return OAuth2UserPrincipal.from(createUser(userInfo));
        }
        return OAuth2UserPrincipal.from(foundUser.get(), oAuth2User.getAttributes());
    }

    private User createUser(OAuth2UserInfo userInfo) {
        User newUser = User.builder()
            .email(userInfo.getEmail())
            .name(userInfo.getName())
            .role(Role.USER)
            .build();
        return userRepository.save(newUser);
    }
}
