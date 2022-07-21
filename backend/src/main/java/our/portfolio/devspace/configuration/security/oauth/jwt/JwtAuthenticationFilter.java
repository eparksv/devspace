package our.portfolio.devspace.configuration.security.oauth.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(
       @NonNull HttpServletRequest request,
       @NonNull HttpServletResponse response,
       @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String resolvedToken = resolveToken(request);

        if (StringUtils.hasText(resolvedToken) && tokenProvider.validateToken(resolvedToken)) {
            Authentication authentication = tokenProvider.getAuthentication(resolvedToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("Authenticated user: {}, uri: {}", authentication.getName(), request.getRequestURI());
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (isValidHeader(bearerToken)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean isValidHeader(String header) {
        return header != null && header.startsWith("Bearer ");
    }
}