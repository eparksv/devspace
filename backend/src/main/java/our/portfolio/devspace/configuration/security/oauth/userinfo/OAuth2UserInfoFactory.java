package our.portfolio.devspace.configuration.security.oauth.userinfo;

import java.util.Map;

public final class OAuth2UserInfoFactory {

    private OAuth2UserInfoFactory() {
        throw new IllegalStateException("이 클래스를 인스턴스화하지 마세요.");
    }

    /**
     * 프로바이더에게 받은 인증 정보를 반환합니다.
     * <p>
     * {@code attributes}는 프로바이더가 보낸 인증된 사용자의 세부정보를 담고 있습니다.
     *
     * @param registrationId 프로바이더 ID
     * @param attributes 최종 사용자(리소스 소유자)의 속성
     * @return 인증 정보
     */
    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) {
        if (OAuth2Provider.GOOGLE.equalsWith(registrationId)) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (OAuth2Provider.GITHUB.equalsWith(registrationId)) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new IllegalArgumentException(String.format("%s는 알 수 없는 프로바이더 타입입니다.", registrationId));
        }
    }
}