package our.portfolio.devspace.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import our.portfolio.devspace.domain.like.dto.CreateLikeResponse;
import our.portfolio.devspace.domain.like.entity.Like;

@Mapper(uses = {EntityMapper.class})
public interface LikeMapper {

    CreateLikeResponse toCreateLikeResponse(Like like);

    @Mapping(source = "postId", target = "post", qualifiedBy = {EntityMapper.IdToEntity.class})
    @Mapping(source = "userId", target = "profile", qualifiedBy = {EntityMapper.IdToEntity.class})
    Like toEntity(Long postId, Long userId);
}
