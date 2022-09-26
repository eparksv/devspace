package our.portfolio.devspace.utils;

import static com.epages.restdocs.apispec.ResourceDocumentation.headerWithName;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.FieldDescriptors;
import com.epages.restdocs.apispec.HeaderDescriptorWithType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import our.portfolio.devspace.configuration.WebSecurityConfiguration;
import our.portfolio.devspace.configuration.security.oauth.handler.OAuth2AuthenticationFailureHandler;
import our.portfolio.devspace.configuration.security.oauth.handler.OAuth2AuthenticationSuccessHandler;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtAccessDeniedHandler;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtAuthenticationEntryPoint;
import our.portfolio.devspace.configuration.security.oauth.jwt.JwtTokenProvider;
import our.portfolio.devspace.configuration.security.oauth.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import our.portfolio.devspace.configuration.security.oauth.service.CustomOAuth2UserService;

public class ControllerTestUtils {

    public static FieldDescriptors fieldDescriptorsWithMessage(FieldDescriptors baseDescriptor) {
        return new FieldDescriptors(fieldWithPath("message").description("메시지").type(JsonFieldType.STRING))
            .andWithPrefix("data.", baseDescriptor.getFieldDescriptors().toArray(new FieldDescriptor[0]));
    }

    public static HeaderDescriptorWithType authorizationHeader() {
        return headerWithName("Authorization").description("Access Token");
    }

    public static HeaderDescriptorWithType contentTypeApplicationJsonHeader() {
        return headerWithName("Content-Type").description("application/json");
    }

    public static String authorizationToken() {
        return "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    }

    @Import(WebSecurityConfiguration.class)
    @MockBean({
        JwtTokenProvider.class,
        JwtAuthenticationEntryPoint.class,
        JwtAccessDeniedHandler.class,
        CustomOAuth2UserService.class,
        HttpCookieOAuth2AuthorizationRequestRepository.class,
        OAuth2AuthenticationSuccessHandler.class,
        OAuth2AuthenticationFailureHandler.class
    })
    public static class WebSecurityTestConfiguration {

    }
}
