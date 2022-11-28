package our.portfolio.devspace.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import our.portfolio.devspace.configuration.security.oauth.handler.OAuth2AuthenticationFailureHandler;
import our.portfolio.devspace.configuration.security.oauth.handler.OAuth2AuthenticationSuccessHandler;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtAccessDeniedHandler;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtAuthenticationEntryPoint;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtAuthenticationFilter;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import our.portfolio.devspace.configuration.security.oauth.service.CustomOAuth2UserService;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfiguration {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2UserService oauthUserService;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oauth2AuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable().and() // H2 콘솔 사용 설정
            .authorizeHttpRequests(authorize -> authorize
                .antMatchers(HttpMethod.POST, "/api/profiles").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/jobs").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/posts").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/api/like").hasRole("USER")
                .anyRequest().permitAll()
            )
            .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
            .and()
            .cors().configurationSource(corsConfigurationSource()).and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .oauth2Login()
                .authorizationEndpoint()
                    .baseUri("/oauth2/authorization")
                    .authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)
                    .and()
                .redirectionEndpoint()
                    .baseUri("/*/oauth2/code/*")
                    .and()
                .userInfoEndpoint()
                    .userService(oauthUserService)
                    .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oauth2AuthenticationFailureHandler)
                .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
