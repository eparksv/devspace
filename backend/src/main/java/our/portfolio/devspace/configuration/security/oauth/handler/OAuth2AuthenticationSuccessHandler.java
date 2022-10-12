package our.portfolio.devspace.configuration.security.oauth.handler;

import static our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository.REFRESH_TOKEN_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import our.portfolio.devspace.common.utils.CookieUtils;
import our.portfolio.devspace.configuration.security.oauth.domain.OAuth2UserPrincipal;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.profile.entity.Profile;
import our.portfolio.devspace.domain.profile.repository.ProfileRepository;
import our.portfolio.devspace.domain.user.entity.UserRefreshToken;
import our.portfolio.devspace.domain.user.repository.UserRefreshTokenRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final ProfileRepository profileRepository;
    private final HttpCookieOAuth2AuthorizationRequestRepository authorizationRequestRepository;

    @Value("${security.oauth.default-redirect-uri}")
    private String defaultRedirectUri;

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

    private String saveRefreshToken(OAuth2UserPrincipal principal) {
        String refreshToken = tokenProvider.createRefreshToken(principal);

        Optional<UserRefreshToken> userRefreshToken = userRefreshTokenRepository
            .findByUserId(principal.getId());
        if (userRefreshToken.isPresent()) {
            userRefreshToken.get().reissueToken(refreshToken);
        } else {
            userRefreshTokenRepository.save(new UserRefreshToken(principal.getId(), refreshToken));
        }
        return refreshToken;
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        OAuth2UserPrincipal principal = (OAuth2UserPrincipal) authentication.getPrincipal();

        String targetUrl = defaultRedirectUri;
        String accessToken = tokenProvider.createAccessToken(principal);
        String refreshToken = saveRefreshToken(principal);
        Integer jobId = profileRepository.findById(principal.getId())
            .map(Profile::getJob)
            .map(Job::getId).orElse(null);

        addRefreshTokenCookie(request, response, refreshToken);

        log.info("accessToken: {}", accessToken);
        return UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("token", accessToken)
            .queryParam("id", principal.getId())
            .queryParam("job", jobId)
            .build().toUriString();
    }

    private void addRefreshTokenCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) tokenProvider.getRefreshExpirationTime() / 60;

        CookieUtils.deleteCookie(request, response, REFRESH_TOKEN_PARAM_COOKIE_NAME);
        CookieUtils.addCookie(response, REFRESH_TOKEN_PARAM_COOKIE_NAME, refreshToken, cookieMaxAge);
    }
}