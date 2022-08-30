package our.portfolio.devspace.configuration.security.oauth.domain;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isUserIdAnnotation = parameter.hasParameterAnnotation(UserId.class);
        boolean isLongType = parameter.getParameterType().isAssignableFrom(Long.class);
        return isUserIdAnnotation && isLongType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return Long.parseLong(userId);
    }
}
