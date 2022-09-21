package our.portfolio.devspace.domain.post.service.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery.Filter;

@Component
public class PostPaginationServiceFactory {

    private final Map<String, PostPaginationService> services = new HashMap<>();

    public PostPaginationServiceFactory(List<PostPaginationService> services) {
        for (PostPaginationService service : services) {
            this.services.put(service.getClass().getSimpleName(), service);
        }
    }

    public PostPaginationService getService(Filter filter) {
        return this.services.get(filter.getServiceName());
    }

}
