package our.portfolio.devspace.configuration.security.oauth.userinfo;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected final Map<String, Object> attributes;

    protected OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    /**
     * 인증 정보에서 프로바이더를 반환합니다.
     * <p>
     * 프로바이더는 Google, Kakao, Naver 등과 같은 OAuth 서비스를 제공하는 제공자를 말합니다.
     *
     * @return 프로바이더 (null이 아님)
     */
    public abstract OAuth2Provider getProvider();

    public abstract String getSubject();

    public abstract String getName();

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}

