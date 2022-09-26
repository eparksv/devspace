package our.portfolio.devspace.domain.post.service.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery.PostFilter;

@Component
public class PostPaginationServiceFactory {

    private final Map<String, PostPaginationService> services = new HashMap<>();

    public PostPaginationServiceFactory(List<PostPaginationService> services) {
        for (PostPaginationService service : services) {
            this.services.put(AopUtils.getTargetClass(service).getSimpleName(), service);
        }
    }

    /**
     * @param filter {@link our.portfolio.devspace.domain.post.dto.GetPostsQuery}의 필드로 저장된 포스팅 필터링 조건
     * @return 필터링 조건에 해당하는 {@link PostPaginationService}의 구현 클래스
     */
    public PostPaginationService getService(PostFilter filter) {
        return this.services.get(filter.getServiceName());
    }

}
