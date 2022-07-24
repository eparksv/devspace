package our.portfolio.devspace.configuration.security.oauth.userinfo;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {

    protected GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public OAuth2Provider getProvider() {
        return OAuth2Provider.GITHUB;
    }

    @Override
    public String getSubject() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }
}
