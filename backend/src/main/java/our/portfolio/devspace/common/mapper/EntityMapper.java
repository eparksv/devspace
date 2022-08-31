package our.portfolio.devspace.common.mapper;

import org.mapstruct.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class EntityMapper {

    private final Repositories repositories;

    public EntityMapper(WebApplicationContext context) {
        this.repositories = new Repositories(context);
    }

    private <T, N extends Number> JpaRepository<T, N> getRepository(Class<T> entityClass) {
        return (JpaRepository<T, N>) repositories.getRepositoryFor(entityClass).orElseThrow(IllegalArgumentException::new);
    }

    @IdToEntity
    public <T, N extends Number> T resolve(N id, @TargetType Class<T> entityClass) {
        return getRepository(entityClass).findById(id).orElseThrow();
    }
}
