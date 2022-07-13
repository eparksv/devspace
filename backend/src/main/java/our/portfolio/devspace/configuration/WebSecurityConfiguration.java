package our.portfolio.devspace.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfiguration {
    @Value("${in-memory-user.name}")
    private String adminName;

    @Value("${in-memory-user.password}")
    private String adminPassword;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .mvcMatchers("/docs/**", "/swagger-ui/**", "/v3/api-docs/**").hasRole("ADMIN")
                .mvcMatchers("/api/hello").authenticated()
                .anyRequest().denyAll()
        ).formLogin(withDefaults()).oauth2Login(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username(adminName).password(adminPassword).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(admin);
    }


}
