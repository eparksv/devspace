package our.portfolio.devspace.configuration.security.oauth.handler;

import static our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;
import static our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository.REFRESH_TOKEN_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import our.portfolio.devspace.common.utils.CookieUtils;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import our.portfolio.devspace.domain.user.UserRefreshToken;
import our.portfolio.devspace.repository.UserRefreshTokenRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final HttpCookieOAuth2AuthorizationRequestRepository authorizationRequestRepository;

    @Value("${security.oauth.authorized-redirect-uri}")
    String[] authorizedRedirectUris;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            log.error("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private String getTargetUrl(HttpServletRequest request) {
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        return redirectUri.orElse(getDefaultTargetUrl());
    }

    private String saveRefreshToken(Authentication authentication) {
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        OAuth2User user = (OAuth2User)authentication.getPrincipal();

        Optional<UserRefreshToken> userRefreshToken = userRefreshTokenRepository.findByEmail(user.getName());
        if (userRefreshToken.isPresent()) {
            userRefreshToken.get().reissueToken(refreshToken);
        } else {
            userRefreshTokenRepository.save(new UserRefreshToken(user.getName(), refreshToken));
        }
        return refreshToken;
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String targetUrl = getTargetUrl(request);
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = saveRefreshToken(authentication);

        addRefreshTokenCookie(request, response, refreshToken);

        log.info("accessToken: {}", accessToken);
        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", accessToken)
            .build().toUriString();
    }

    private void addRefreshTokenCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) tokenProvider.getRefreshExpirationTime() / 60;

        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN_PARAM_COOKIE_NAME);
        CookieUtils.addCookie(response, REFRESH_TOKEN_PARAM_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return Arrays.stream(authorizedRedirectUris)
            .anyMatch(authorizedRedirectUri -> {
                URI authorizedUri = URI.create(authorizedRedirectUri);
                return authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                    && authorizedUri.getPort() == clientRedirectUri.getPort();
            });
    }
}