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

    /**
     * Entity의 {@link Class}로 해당 Entity의 Repository를 찾아서 반환한다.<br> ex) Post -> PostRepository, Category -> CategoryRepository
     *
     * @param entityClass {@link Class} 찾을 Repository의 Entity {@link Class}
     * @return {@link JpaRepository} entityClass의 Repository
     */
    private <T, N extends Number> JpaRepository<T, N> getRepository(Class<T> entityClass) {
        return (JpaRepository<T, N>) repositories.getRepositoryFor(entityClass).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Repository에서 id로 entity를 찾아서 반환한다.
     *
     * @param id          검색할 id
     * @param entityClass 찾을 entity의 {@link Class}
     * @return 검색된 entity
     */
    @IdToEntity
    public <T, N extends Number> T resolve(N id, @TargetType Class<T> entityClass) {
        return getRepository(entityClass).findById(id)
            .orElseThrow(() -> new CustomException(entityClass.getSimpleName() + " ID " + id + "에 해당하는 리소스가 없습니다.", ErrorDetail.ENTITY_NOT_FOUND));
    }

    /**
     * Entity id를 Entity로 매핑할 때 @Mapping(qualifiedBy)의 값으로 넣는다.
     */
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    public @interface IdToEntity {

    }
}
