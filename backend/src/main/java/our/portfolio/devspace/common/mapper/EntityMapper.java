package our.portfolio.devspace.common.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.Qualifier;
import org.mapstruct.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class EntityMapper {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface IdToEntity {

    }

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
