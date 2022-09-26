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
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

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
        return getRepository(entityClass).findById(id)
            .orElseThrow(() -> new CustomException(entityClass.getSimpleName() + " ID " + id + "에 해당하는 리소스가 없습니다.", ErrorDetail.ENTITY_NOT_FOUND));
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface IdToEntity {

    }
}
