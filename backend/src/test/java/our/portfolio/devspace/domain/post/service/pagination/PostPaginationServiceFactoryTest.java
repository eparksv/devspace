package our.portfolio.devspace.domain.post.service.pagination;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import our.portfolio.devspace.common.mapper.PostMapper;
import our.portfolio.devspace.domain.post.dto.GetPostsQuery.PostFilter;
import our.portfolio.devspace.domain.post.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
class PostPaginationServiceFactoryTest {

    @Mock
    private PostRepository repository;

    @Mock
    private PostMapper mapper;

    @ParameterizedTest
    @EnumSource
    void getService(PostFilter filter) {
        // ** Given **
        Map<PostFilter, PostPaginationService> serviceMap = new HashMap<>();
        serviceMap.put(PostFilter.NONE, new RecentPostPaginationService(repository, mapper));

        PostPaginationServiceFactory factory = new PostPaginationServiceFactory(new ArrayList<>(serviceMap.values()));

        // ** When **
        PostPaginationService service = factory.getService(filter);

        // ** Then **
        assertThat(service).isEqualTo(serviceMap.get(filter));
    }

}